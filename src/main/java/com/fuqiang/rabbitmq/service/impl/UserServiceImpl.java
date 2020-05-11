package com.fuqiang.rabbitmq.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fuqiang.basecommons.util.IDWorkerUtil;
import com.fuqiang.rabbitmq.mapper.UserMapper;
import com.fuqiang.rabbitmq.model.User;
import com.fuqiang.rabbitmq.service.MQProducer;
import com.fuqiang.rabbitmq.service.UserService;
import com.netflix.discovery.converters.Auto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>Title: UserServiceImpl</p>
 * <p>Description: UserServiceImpl</p>
 *
 * @author Fuqiang
 * @version 0.0.0.1
 */
@Service
@Slf4j
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    private IDWorkerUtil idWorkerUtil;
    @Autowired
    private MQProducer producer;

    /**
     * 新增user
     *
     * @param user
     */
    @Override
    public void addUser(User user) {
        user.setId(idWorkerUtil.getIdStr());
        try {
            producer.send(user);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
}
