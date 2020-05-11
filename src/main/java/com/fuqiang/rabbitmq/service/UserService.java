package com.fuqiang.rabbitmq.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.fuqiang.rabbitmq.model.User;

/**
 * <p>Title: UserService</p>
 * <p>Description: UserService</p>
 *
 * @author Fuqiang
 * @version 0.0.0.1
 */
public interface UserService extends IService<User> {

    /**
     * 新增user
     *
     * @param user
     */
    void addUser(User user);
}
