package com.ml.callback;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.stereotype.Component;

@Component
public class ConfirmCallback implements RabbitTemplate.ConfirmCallback {

    private Logger logger = LoggerFactory.getLogger(ConfirmCallback.class);

    /**
     * Confirmation callback.
     *
     * @param correlationData correlation data for the callback.
     * @param ack             true for ack, false for nack
     * @param cause           An optional cause, for nack, when available, otherwise null.
     */
    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String cause) {
        logger.info("ConfirmCallback:=====================\n correlationData:{},ack:{},cause:{}", correlationData, ack, cause);
    }
}
