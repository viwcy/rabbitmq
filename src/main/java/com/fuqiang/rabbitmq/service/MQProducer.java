package com.fuqiang.rabbitmq.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fuqiang.rabbitmq.callback.MyConfirmCallback;
import com.fuqiang.rabbitmq.config.RabbitMQConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.core.MessageDeliveryMode;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * <p>Title: MQProducer</p>
 * <p>Description: MQProducer</p>
 *
 * @author Fuqiang
 * @version 0.0.0.1
 */
@Component
public class MQProducer {

    private static final Logger logger = LoggerFactory.getLogger(MyConfirmCallback.class);

    @Autowired
    private RabbitTemplate rabbitTemplate;
    @Autowired
    private ObjectMapper objectMapper;

    public void send(Object object) throws JsonProcessingException {
        // 自定义消息唯一标识
        CorrelationData correlationData = new CorrelationData();
        correlationData.setId(UUID.randomUUID().toString().replace("-", ""));
        Message message = MessageBuilder
                .withBody(objectMapper.writeValueAsBytes(object))
                .setDeliveryMode(MessageDeliveryMode.NON_PERSISTENT)
                .build();
        /**
         *   没有正确的路由键位，才会执行returnCallback
         */
        this.rabbitTemplate.convertAndSend(RabbitMQConfig.USER_EXCHANGE, RabbitMQConfig.USER_ROUTE_KEY, message, correlationData);
        logger.info("发送消息，回调ID: {}", correlationData.getId());
    }

}
