package com.fuqiang.rabbitmq.callback;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

import java.io.UnsupportedEncodingException;

/**
 * <p>Title: MyReturnCallback</p>
 * <p>Description: MyReturnCallback</p>
 * <p>
 * //TODO 消息是否成功被路由Routing，只有当没有该route的时候才会return回调
 *
 * @author Fuqiang
 * @version 0.0.0.1
 */
public class MyReturnCallback implements RabbitTemplate.ReturnCallback {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * 路由响应回调(交换机的消息是否被正确路由到queue)
     */
    @Override
    public void returnedMessage(Message message, int replyCode, String replyText, String exchange, String routingKey) {
        try {
            logger.info("消息内容: {}", new String(message.getBody(), "UTF-8"));
            logger.info("响应编码: {}", replyCode);
            logger.info("响应内容: {}", replyText);
            logger.info("交换机: {}", exchange);
            logger.info("路由键: {}", routingKey);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
}
