package com.fuqiang.rabbitmq.model;

import com.fuqiang.basecommons.pojo.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <p>Title: User</p>
 * <p>Description: User</p>
 *
 * @author Fuqiang
 * @version 0.0.0.1
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User extends BaseEntity<User> {

    private String name;
    private String gender;
    private int age;
}
