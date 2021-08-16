package com.ml.timi.controller;

import com.ml.timi.config.enums.ResultEnum;
import com.ml.timi.model.entity.User;
import com.ml.timi.model.request.LoginRequest;
import com.ml.timi.service.UserService;
import com.ml.timi.utils.JsonData;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * ClassName:      UserController
 * Description:    用户类
 * Date:           2021 2021/7/8 14:36
 * Author:         Lin
 * Copyright:      Lin
 */
@RestController
@RequestMapping("api/v1/pri/user")
public class UserController {

    @Resource
    public UserService userService;

    /**
     * @Method          register
     * @Description     用户注册
     * @Author          Lin
     * @Date            2021/7/8 15:30
     * @Version         1.0.0
     * @param          registerUserInfo     用户注册信息
     * @return          com.ml.timi.utils.JsonData
     */
    @PostMapping("register")
    public JsonData register(@RequestBody User registerUserInfo) {

        String name = registerUserInfo.getName();

        int rows = userService.register(registerUserInfo);

        return rows == 1 ? JsonData.resultData(ResultEnum.INSERT_SUCCESS, name + ":注册成功") : JsonData.resultData(ResultEnum.INSERT_ERROR, name + "注册失败");
    }


    /**
     * @Method          login
     * @Description     用户登录
     *                  <登录前进行 验证 >
     * @Author          Lin
     * @Date            2021/7/8 15:31
     * @Version         1.0.0
     * @param           loginRequest    接收一个 Json 对象 LoginRequest
     * @return          com.ml.timi.utils.JsonData
     */
    @PostMapping("login")
    public JsonData login(@RequestBody LoginRequest loginRequest) {

        String token = userService.checkLogin(loginRequest.getLogin(), loginRequest.getPwd());
        return token == null ? JsonData.resultData(ResultEnum.LOGIN_ERROR) : JsonData.resultData(ResultEnum.LOGIN_SUCCESS,token);
    }


    @GetMapping("A")
    public JsonData findUserByToken(HttpServletRequest request) {
        Integer userId = (Integer) request.getAttribute("user_id");
        if (userId == null) {
            return JsonData.resultData(ResultEnum.RECORD_IS_NULL);
        }
        User user = userService.findUserByToken(userId);
        return JsonData.resultData(ResultEnum.SELECT_SUCCESS,user);
    }

}
