/**
 * ClassName:
 * Description:
 * Date:           2021 2021/12/10 16:14
 * Author:         Lin
 * Copyright:      Lin
 */


package com.ml.timi.service.impl;

import com.ml.timi.service.AuthService;

import javax.jws.WebService;



@WebService(name = "AuthService",
        targetNamespace = "http://service.timi.ml.com",//接口命名空间
        endpointInterface = "com.ml.timi.service.AuthService")  //接口名称
public class AuthServiceImpl implements AuthService {
    @Override
    public String echo(String message) {
        return "SHUCHU ："+message;
    }
}
