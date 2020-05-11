package com.fuqiang.rabbitmq.callback;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

/**
 * <p>Title: MyConfirmCallback</p>
 * <p>Description: MyConfirmCallback</p>
 * <p>
 * //TODO 消息是否成功到达交换机Exchange
 *
 * @author Fuqiang
 * @version 0.0.0.1
 */
public class MyConfirmCallback implements RabbitTemplate.ConfirmCallback {

    private static final Logger logger = LoggerFactory.getLogger(MyConfirmCallback.class);

    /**
     * 发送确认的回调方法(消息是否到达交换机)
     */
    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String cause) {
        if (ack) {
            logger.info("消息成功到达交换机，回调ID为: {}", correlationData.getId());
        } else {
            logger.info("消息未成功到达交换机，原因: {}", cause);
        }
    }
}
