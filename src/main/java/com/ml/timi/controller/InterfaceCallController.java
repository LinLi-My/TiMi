/**
 * ClassName:      InterfaceCallController
 * Description:    webservice接口调用
 * Date:           2021 2021/7/20 9:07
 * Author:         Lin
 * Copyright:      Lin
 */
package com.ml.timi.controller;

import com.ml.timi.config.template.Module;
import com.ml.timi.config.webservices.WebServiceCallConfig;
import com.ml.timi.config.webservices.WebServiceCallMethodConfig;
import com.ml.timi.model.response.ResponseTemplate;
import com.ml.timi.model.entity.User;
import com.ml.timi.model.entity.VideoOrder;
import com.ml.timi.utils.CommonUtils;
import com.ml.timi.utils.JSONUtil;
import com.ml.timi.model.request.RequestTemplate;
import org.apache.cxf.endpoint.Client;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/interfaceCall")
public class InterfaceCallController {

    @Resource
    public User user;
    @Resource
    public RequestTemplate logMiner;

    /** 调用接口地址 */
    public String URL;
    /*** 创建客户端 */
    public Client client;
    /** 动态方法 */
    public static String methodName = "";
    /** 请求接口数据 */
    public String requestData = "";
    /** 加密的MD5 */
    public String MD5Encrypt = "";
    /** 响应数据 */
    public String responseData;
    /** 批次标识 */
    public String batchId;
    /** 下传数量 */
    public int downloadCount;
    /** 下传状态 */
    public String downloadStatus = "Error";
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @RequestMapping("callTestWebService")
    public String callTestWebService() {

        URL = WebServiceCallConfig.TestWebService;
        client = WebServiceCallConfig.call(URL);
        methodName = WebServiceCallMethodConfig.register;

        user.setName("张三");
        user.setLogin("100982");
        //将JAVA对象转化换为JSON字符串
        requestData = JSONUtil.objectToJson(user);
        MD5Encrypt = CommonUtils.MD5Encrypt(requestData);
        try {
            Object[] responseDataBack = client.invoke(methodName, requestData, MD5Encrypt);
            String responseData = responseDataBack[0].toString();
            System.out.println(responseData);

        } catch (Exception e) {
            e.printStackTrace();

        }

        return responseData;
    }


    @RequestMapping("callTestWebServiceList")
    public String callTestWbeServiceList() {
        URL = WebServiceCallConfig.TestWebService;
        client = WebServiceCallConfig.call(URL);
        methodName = WebServiceCallMethodConfig.registerList;
        batchId = UUID.randomUUID().toString();

        List<User> userLists = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            user = new User();
            user.setLogin(" " + i);
            user.setName("张三-" + i);
            user.setPwd("98723634twyshg6123--" + i);
            user.setCreateTime(LocalDateTime.now());
            user.setHeadImg("hiyquy.jpg" + i);
            user.setPhone("19988446552--" + i);
            List<VideoOrder> videoOrderList = new ArrayList<>();
            for (int j = 0; j < 2; j++) {
                VideoOrder videoOrder = new VideoOrder();
                videoOrder.setVideoTitle("视频标题-" + j);
                videoOrder.setVideoImg("视频图片-" + j);
                videoOrder.setCreateTime(LocalDateTime.now());
                videoOrder.setOutTradeNo("订单编号-" + j);
                videoOrder.setUserId(j);
                videoOrderList.add(videoOrder);
            }
            userLists.add(user);
        }

        downloadCount = userLists.size();

        //组装下传数据格式，并存日志
        logMiner = RequestTemplate.SetLogMiner(batchId, Module.INPUT, userLists, downloadStatus, downloadCount, "");
        //下传前插入日志 ，并将其日志插入数据库
        LOGGER.info(logMiner.toString());
        //将下传数据转换为JSON
        requestData = JSONUtil.objectToJson(logMiner);
        //将下传数据用MD5加密
        MD5Encrypt = CommonUtils.MD5Encrypt(requestData);

        try {
            //调用接口
            Object[] responseDataBack = client.invoke(methodName, requestData, MD5Encrypt);
            //logMiner = LogMiner.SetLogMiner(batchId, LogModule.INPUT, userLists, LogStatus.SUCCESS, downloadCount, "");
            //处理接口返回数据
            responseData = responseDataBack[0].toString();
            ResponseTemplate respon = new ResponseTemplate();
            respon = JSONUtil.jsonToEntity(responseData, ResponseTemplate.class);

            List<Object> responseDataList = respon.getObjectList();

            List<User> list = new ArrayList<>();


            //回传后插入日志，
            LOGGER.info(respon.toString());

            System.out.println(responseData);
        } catch (Exception e) {
            RequestTemplate.SetLogMiner(batchId, Module.INPUT, userLists, downloadStatus, downloadCount, "");
            return e.toString();
        }

        return responseData;


    }
}
