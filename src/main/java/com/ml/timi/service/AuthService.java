package com.ml.timi.service;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;


@WebService(name = "AuthService",
           targetNamespace = "http://service.timi.ml.com")//与包名相反
public interface AuthService {


    @WebMethod  //进行webservice 方法标注
    public String echo(@WebParam String message);



}
