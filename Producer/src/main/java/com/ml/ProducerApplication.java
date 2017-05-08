package com.ml;

import com.ml.service.ProducerService;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ProducerApplication {

    public static void main(String[] args) throws InterruptedException {
        AbstractApplicationContext cxt = new ClassPathXmlApplicationContext("classpath:spring-rabbitmq.xml");
        ProducerService producerService = cxt.getBean(ProducerService.class);
        producerService.sendMsg("directExchange", "confirm_message", "hahahahah");
        Thread.sleep(1000);
        cxt.destroy();
    }
}
