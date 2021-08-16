package com.ml.timi.service;

import org.springframework.stereotype.Service;

import javax.jws.WebService;

@Service
@WebService( targetNamespace = "http://service.timi.ml.com")
public interface TestWebService {

    /**
     * Method               registerList
     * Description          用户批量插入
     * Author               Lin
     * Date                 2021/7/20 8:53
     * Version              1.0.0
     * @param           requestData    接收请求参数
     * @param           requestMD5     接收的MD5
     * @return          int            返回插入数量
     */
    String registerList(String requestData, String requestMD5);

    /**
     * Method               registerList
     * Description          用户单条插入
     * Author               Lin
     * Date                 2021/7/20 8:53
     * Version              1.0.0
     * @param           requestData    接收请求参数
     * @param           requestMD5     接收的MD5
     * @return          int            返回插入数量
     */
    Object register(String requestData, String requestMD5);


}
