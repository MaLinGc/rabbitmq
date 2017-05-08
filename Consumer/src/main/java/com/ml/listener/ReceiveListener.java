package com.ml.listener;

import com.rabbitmq.client.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.ChannelAwareMessageListener;
import org.springframework.stereotype.Component;

@Component
public class ReceiveListener implements ChannelAwareMessageListener {

    private Logger logger = LoggerFactory.getLogger(ReceiveListener.class);

    /**
     * Callback for processing a received Rabbit message.
     * <p>Implementors are supposed to process the given Message,
     * typically sending reply messages through the given Session.
     *
     * @param message the received AMQP message (never <code>null</code>)
     * @param channel the underlying Rabbit Channel (never <code>null</code>)
     * @throws Exception Any.
     */
    @Override
    public void onMessage(Message message, Channel channel) throws Exception {
        logger.info("receive message : {}", new String(message.getBody()));
        logger.info("receive message properties : {}", message.getMessageProperties());

        /**
         * Acknowledge one or several received
         * messages. Supply the deliveryTag from the {@link com.rabbitmq.client.AMQP.Basic.GetOk}
         * or {@link com.rabbitmq.client.AMQP.Basic.Deliver} method
         * containing the received message being acknowledged.
         * @see com.rabbitmq.client.AMQP.Basic.Ack
         * @param deliveryTag the tag from the received {@link com.rabbitmq.client.AMQP.Basic.GetOk} or {@link com.rabbitmq.client.AMQP.Basic.Deliver}
         * @param multiple true to acknowledge all messages up to and
         * including the supplied delivery tag; false to acknowledge just
         * the supplied delivery tag.
         * @throws java.io.IOException if an error is encountered
         */
        try {
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        } catch (Exception e) {
            e.printStackTrace();

            /**
             * Reject one or several received messages.
             *
             * Supply the <code>deliveryTag</code> from the {@link com.rabbitmq.client.AMQP.Basic.GetOk}
             * or {@link com.rabbitmq.client.AMQP.Basic.GetOk} method containing the message to be rejected.
             * @see com.rabbitmq.client.AMQP.Basic.Nack
             * @param deliveryTag the tag from the received {@link com.rabbitmq.client.AMQP.Basic.GetOk} or {@link com.rabbitmq.client.AMQP.Basic.Deliver}
             * @param multiple true to reject all messages up to and including
             * the supplied delivery tag; false to reject just the supplied
             * delivery tag.
             * @param requeue true if the rejected message(s) should be requeued rather
             * than discarded/dead-lettered
             * @throws java.io.IOException if an error is encountered
             */
            channel.basicNack(message.getMessageProperties().getDeliveryTag(),false,false);
        }
    }

}
