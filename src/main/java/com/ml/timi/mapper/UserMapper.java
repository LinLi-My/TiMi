package com.ml.timi.mapper;

import com.ml.timi.model.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {

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


    User checkLogin(String login, String pwd);

    User findByUserToken(Integer userId);
}
