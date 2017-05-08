package com.ml.service;

import com.ml.domain.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.core.MessagePropertiesBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring-rabbitmq.xml"})
public class ProducerServiceTest {

    @Autowired
    private ProducerService producerService;

    final String exchange = "directExchange";
    final String routingKey = "confirm_message";


    private Message message;
    private User user;

    @Before
    public void init() {
        MessageProperties props = MessagePropertiesBuilder.newInstance()
                                                          .setContentType(MessageProperties.CONTENT_TYPE_TEXT_PLAIN)
                                                          .setMessageId("123").setHeader("bar", "baz").build();
        message = MessageBuilder.withBody("foo".getBytes()).andProperties(props).build();

        user = new User("maling",23,true,new Date());
    }

    /**
     * 全部正确，ConfirmCallback 被回调，correlationData:null,ack:true,cause:null
     *
     * @throws Exception
     */
    @Test
    public void sendMsg() throws Exception {
        producerService.sendMsg(exchange, routingKey, user);
        Thread.sleep(3000);
    }

    /**
     * exchange 错误，ConfirmCallback 被回调，correlationData:null,ack:false,cause:channel error
     *
     * @throws Exception
     */
    @Test
    public void sendMsg1() throws Exception {
        producerService.sendMsg(exchange + "error", routingKey, "hahahahah");
        Thread.sleep(1000);
    }

    /**
     * queue 错误，
     * ConfirmCallback 被回调，correlationData:null,ack:true,cause:null
     * ReturnCallback被回调，message:hahahahah,replyCode:312,replyText:NO_ROUTE,exchange:directExchange,routingKey:confirm_messageerror
     *
     * @throws Exception
     */
    @Test
    public void sendMsg2() throws Exception {
        producerService.sendMsg(exchange, routingKey + "error", "hahahahah");
        Thread.sleep(1000);
    }

    /**
     * 全部错误
     * ConfirmCallback 被回调，correlationData:null,ack:false,cause:channel error
     *
     * @throws Exception
     */
    @Test
    public void sendMsg3() throws Exception {
        producerService.sendMsg(exchange + "error", routingKey + "error", "hahahahah");
        Thread.sleep(1000);
    }

}