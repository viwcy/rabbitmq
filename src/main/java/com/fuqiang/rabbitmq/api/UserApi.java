package com.fuqiang.rabbitmq.api;

import com.fuqiang.basecommons.common.BaseController;
import com.fuqiang.basecommons.common.ResultEntity;
import com.fuqiang.rabbitmq.model.User;
import com.fuqiang.rabbitmq.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>Title: UserApi</p>
 * <p>Description: UserApi</p>
 *
 * @author Fuqiang
 * @version 0.0.0.1
 */
@RestController
@RequestMapping("/rabbitmq/user")
public class UserApi extends BaseController {

    @Autowired
    private UserService userService;

    @PostMapping("/add")
    private ResultEntity addUser(@RequestBody User user) {
        userService.addUser(user);
        return success();
    }
}
