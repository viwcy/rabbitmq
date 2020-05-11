package com.fuqiang.rabbitmq.config;

import com.fuqiang.rabbitmq.callback.MyConfirmCallback;
import com.fuqiang.rabbitmq.callback.MyReturnCallback;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.SerializerMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

/**
 * <p>Title: RabbitMQConfig</p>
 * <p>Description: RabbitMQConfig</p>
 *
 * @author Fuqiang
 * @version 0.0.0.1
 */
@Configuration
public class RabbitMQConfig {

    public static final String USER_QUEUE_A = "user_queue_A";
    public static final String USER_QUEUE_B = "user_queue_B";
    public static final String USER_EXCHANGE = "user_exchange";
    public static final String USER_ROUTE_KEY = "user_route_key";

    @Bean
    public Queue userQueueA() {
        return new Queue(USER_QUEUE_A);
    }

    @Bean
    public Queue userQueueB() {
        return new Queue(USER_QUEUE_B);
    }

    @Bean
    public FanoutExchange userFanoutExchange() {
        return new FanoutExchange(USER_EXCHANGE);
    }

    @Bean
    public Binding bindingExchange() {
        return BindingBuilder.bind(userQueueA()).to(userFanoutExchange());
    }

    @Bean("rabbitTemplate")
    @Scope("prototype")
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate template = new RabbitTemplate(connectionFactory);
        template.setMandatory(true);
        template.setMessageConverter(new SerializerMessageConverter());
        /**
         *   一个rabbitTemplate只设置一次回调方法
         */
        template.setReturnCallback(new MyReturnCallback());
        template.setConfirmCallback(new MyConfirmCallback());
        return template;
    }
}
