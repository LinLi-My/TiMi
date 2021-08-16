package com.ml.timi.service;

import com.ml.timi.model.entity.User;
import org.springframework.stereotype.Service;

/**
 * ClassName:      UserService
 * Description:    用户接口
 * Date:           2021 2021/7/8 14:36
 * Author:         Lin
 * Copyright:      Lin
 */
@Service
public interface UserService {
    /**
     * Method               register
     * Description          用户注册接口
     * Author               Lin
     * Date                 2021/7/19 21:15
     * Version              1.0.0
     * @param           registerUserInfo    用户注册信息
     * @return int         返回插入数量
     */
    int register(User registerUserInfo);


    User findByPhone(String phone);

    String checkLogin(String login, String pwd);


    User findUserByToken(Integer userId);
}
