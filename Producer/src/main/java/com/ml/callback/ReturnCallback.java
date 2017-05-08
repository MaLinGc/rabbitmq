package com.ml.callback;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

/**
 * 确认后回调
 */
@Component
public class ReturnCallback implements RabbitTemplate.ReturnCallback {

    private Logger logger = LoggerFactory.getLogger(ReturnCallback.class);

    /**
     * Returned message callback.
     *
     * @param message    the returned message.
     * @param replyCode  the reply code.
     * @param replyText  the reply text.
     * @param exchange   the exchange.
     * @param routingKey the routing key.
     */
    @Override
    public void returnedMessage(Message message, int replyCode, String replyText, String exchange, String routingKey) {
        logger.info("ReturnCallback:=====================\n message:{},replyCode:{},replyText:{},exchange:{},routingKey:{}",
                new String(message.getBody()), replyCode, replyText, exchange, routingKey);
    }
}
