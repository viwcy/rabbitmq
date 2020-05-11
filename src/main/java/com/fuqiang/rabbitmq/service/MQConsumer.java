package com.fuqiang.rabbitmq.service;

import com.alibaba.fastjson.JSON;
import com.fuqiang.rabbitmq.config.RabbitMQConfig;
import com.fuqiang.rabbitmq.mapper.UserMapper;
import com.fuqiang.rabbitmq.model.User;
import com.rabbitmq.client.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

/**
 * <p>Title: MQConsumer</p>
 * <p>Description: MQConsumer</p>
 *
 * @author Fuqiang
 * @version 0.0.0.1
 */
@Component
public class MQConsumer {

    private static final Logger logger = LoggerFactory.getLogger(MQConsumer.class);

    @Autowired
    private UserMapper userMapper;

    @RabbitHandler
    @RabbitListener(queues = {RabbitMQConfig.USER_QUEUE_A, RabbitMQConfig.USER_QUEUE_B})
    public void consume(Channel channel, Message message) {
        String content = null;
        try {
            content = new String(message.getBody(), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        logger.info("收到来自USER_QUEUE队列的消息: {}", content);
        User user = JSON.parseObject(content, User.class);
        userMapper.insert(user);
        logger.info("user添加成功: {}", JSON.toJSONString(user));
        logger.info("消息消费成功");
        /**
         *  若该条消息已经被成功消费，则从队列删除。否则，下次接着消费这条消息，直到被成功消费，才从队列删除(跟是否开启重试无关)
         *  比RabbitMQ默认ack方式更加安全
         */
        try {
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
            logger.info("手动ack确认成功");
        } catch (IOException e) {
            e.printStackTrace();
            logger.info("手动ack确认失败，原因: {}", e.getCause());
        }
    }
}
