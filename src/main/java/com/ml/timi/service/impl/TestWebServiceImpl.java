/**
 * ClassName:
 * Description:
 * Date:           2021 2021/7/19 15:51
 * Author:         Lin
 * Copyright:      Lin
 */


package com.ml.timi.service.impl;

import com.ml.timi.config.template.LogDirectory;
import com.ml.timi.config.template.Module;
import com.ml.timi.config.template.OperationType;
import com.ml.timi.config.template.Status;
import com.ml.timi.mapper.UserMapper;
import com.ml.timi.model.entity.User;
import com.ml.timi.model.entity.UserTest;
import com.ml.timi.model.log.request.RequestTemplate;
import com.ml.timi.model.log.response.ResponseBody;
import com.ml.timi.model.log.response.ResponseTemplate;
import com.ml.timi.utils.InterfaceResponse;
import com.ml.timi.service.LogService;
import com.ml.timi.service.TestWebService;
import com.ml.timi.utils.*;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.ibatis.session.SqlSessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.jws.WebService;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static com.ml.timi.config.template.CommonVar.NATURALKEY;
import static com.ml.timi.config.template.CommonVar.OPERATOR;
import static com.ml.timi.config.template.CommonVar.COUNT;
import static com.ml.timi.config.template.CommonVar.REQUEST;
import static com.ml.timi.config.template.CommonVar.RESPONSE;
import static com.ml.timi.config.template.CommonVar.LOGINFO;
import static com.ml.timi.config.template.CommonVar.LOGERROR;
import static com.ml.timi.config.template.CommonVar.RUNTIME;

@Service
@WebService(serviceName = "WebServiceTest",
        targetNamespace = "http://service.timi.ml.com",
        endpointInterface = "com.ml.timi.service.TestWebService")
public class TestWebServiceImpl implements TestWebService {
    @Resource
    public InterfaceResponse interfaceResponse;
    @Resource
    private LogTemplate logTemplate;
    @Resource
    private LogService logService;
    @Resource
    public UserMapper userMapper;
    @Resource
    public User user;
    @Resource
    SqlSessionFactory sqlSessionFactory;
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());


    @Override
    public String registerList(String requestData, String requestMD5) throws Exception {
        //校验请求数据
        JsonData jsonData = CommonUtils.CheckInterfaceParam(requestData, requestMD5);
        String responseError = "状态码：" + jsonData.getCode() + "\n" + "信息：" + jsonData.getMsg();

        if (jsonData.getCode() != 3000) {
            throw new Exception(responseError);
        }
        //状态码：3000，则校验数据通过
        else {


            RequestTemplate requestTemplate = new RequestTemplate();
            List<UserTest> requestBodyList = new ArrayList<>();

            //将请求的Json数据转换为对象
            requestTemplate = JSONUtil.jsonToEntity(requestData, RequestTemplate.class);
            //将请求体的Json数据转换为对象
            requestBodyList = JSONUtil.jsonToList(requestTemplate.getRequestBody(), UserTest.class);

            //将requestTemplate数据存储到数据库

            //将requestTemplate写入日志
            if (ObjectUtils.isEmpty(requestBodyList)) {
                ResponseTemplate responseTemplate = new ResponseTemplate.ResponseTemplateBuilder()
                        .setBatchId(requestTemplate.getBatchId())
                        .setModule(requestTemplate.getModule())
                        .setResponseStatus(Status.ERROR)
                        .setResponseStatusMessage("请求体（ requestBody）数据为空，请检查！")
                        .setResponseBodyCount(0)
                        .setResponseBodySuccessCount(0)
                        .setResponseBodyErrorCount(0)
                        .setResponseTime(LocalDateTime.now())
                        .setResponseBody(null)
                        .build();
                return JSONUtil.objectToJson(responseTemplate);
            } else {
                for (UserTest userTest : requestBodyList) {


                        //将requestBodyList数据存储到数据库
                        //单条循环插入，。。123
                        //错误数据回滚后，并记录错误日志信息，返回响应123



                }


                //将requestBodyList写入日志
            }


            ResponseTemplate responseTemplate = new ResponseTemplate.ResponseTemplateBuilder()
                    .setBatchId(requestTemplate.getBatchId())
                    .setModule(requestTemplate.getModule())
                    .setResponseStatus(Status.SUCCESS)
                    .setResponseStatusMessage("成功")
                    .setResponseBodyCount(0)
                    .setResponseBodySuccessCount(0)
                    .setResponseBodyErrorCount(0)
                    .setResponseTime(LocalDateTime.now())
                    .setResponseBody(null)
                    .build();


            List<RequestTemplate> requestTemplateList = new ArrayList<>();


            List<User> userList = new ArrayList<>();
            List<LogTemplate> logTemplateList = new ArrayList<>();
            List<String> RESPONSEList = new ArrayList<>();
            //将请求数据转为对象集合


            //如果转换对象为空则返回转换失败

            requestTemplateList = JSONUtil.jsonToList(requestData, RequestTemplate.class);


            LocalDateTime a = LocalDateTime.now();
            for (User user : userList) {
                REQUEST = JSONUtil.objectToJson(user);
                NATURALKEY = user.getLogin();
                OPERATOR = user.getName();
                LOGINFO = JSONUtil.objectToJson(user);
                LocalDateTime startLocalDateTime = LocalDateTime.now();
                try {
                    COUNT = userMapper.register(user);
                    LocalDateTime endLocalDateTime = LocalDateTime.now();
                    //操作成功后 处理响应数据
                    RESPONSE = InterfaceResponse.responseSuccess(NATURALKEY);
                    RUNTIME = DateTool.TimeCalculation(startLocalDateTime, endLocalDateTime, DateTool.UNIT_HOURS);
                    //插入正常日志
                    logTemplate = LogTemplate.SetLogTemplate(
                            NATURALKEY,                     //业务主键
                            OperationType.INSERT,        //操作类型
                            Module.INPUT,                //模块
                            OPERATOR,                       //操作人
                            LogDirectory.INPUT,             //日志目录
                            Status.SUCCESS,              //状态
                            COUNT,                          //数量
                            REQUEST,                        //请求数据
                            RESPONSE,                       //响应数据
                            LOGINFO,                        //日志信息
                            RUNTIME                         //运行时间
                    );
                    LOGGER.info(logTemplate.toString());
                    logTemplateList.add(logTemplate);
                    RESPONSEList.add(RESPONSE);
                } catch (Exception e) {

                    LOGERROR = e.toString();
                    RESPONSE = InterfaceResponse.responseError(NATURALKEY, LOGERROR);
                    //插入错误日志
                    logTemplate = LogTemplate.SetLogTemplate(
                            NATURALKEY,                     //业务主键
                            OperationType.INSERT,        //操作类型
                            Module.INPUT,                //模块
                            OPERATOR,                       //操作人
                            LogDirectory.INPUT,             //日志目录
                            Status.ERROR,                //日志状态
                            COUNT,                          //数量
                            REQUEST,                        //请求数据
                            RESPONSE,                       //响应数据
                            LOGINFO,                        //日志信息
                            RUNTIME                         //运行时间
                    );
                    LOGGER.error(logTemplate.toString());
                    return RESPONSE;

                } finally {
                    //插入日志到数据库
                    logService.insertLogInfoList(logTemplateList);
                }

            }
            LocalDateTime b = LocalDateTime.now();

            System.out.println("一共用时计算：" + DateTool.TimeCalculation(a, b, DateTool.UNIT_HOURS));
            return JSONUtil.objectToJson(RESPONSEList);


        }

    }


    @Override
    public Object register(String requestData, String requestMD5) throws Exception {

        //校验请求数据
        JsonData jsonData = CommonUtils.CheckInterfaceParam(requestData, requestMD5);
        //将校验后的返回数据转换为JSON字符串
        RESPONSE = JSONUtil.objectToJson(jsonData);

        //状态码：3000，则校验数据通过
        if (jsonData.getCode() == 3000) {

            //将请求数据转为对象
            user = JSONUtil.jsonToEntity(requestData, User.class);
            user.setPwd(CommonUtils.MD5Encrypt(user.getPwd()));
            user.setCreateTime(LocalDateTime.now());
            NATURALKEY = user.getLogin();
            OPERATOR = user.getName();
            REQUEST = JSONUtil.objectToJson(user);
            LOGINFO = JSONUtil.objectToJson(user);

            try {

                LocalDateTime startLocalDateTime = LocalDateTime.now();
                //数据库插入
                COUNT = userMapper.register(user);
                LocalDateTime endLocalDateTime = LocalDateTime.now();
                //操作成功后 处理响应数据
                RESPONSE = InterfaceResponse.responseSuccess(NATURALKEY);
                RUNTIME = DateTool.TimeCalculation(startLocalDateTime, endLocalDateTime, DateTool.UNIT_HOURS);
                //插入正常日志
                logTemplate = LogTemplate.SetLogTemplate(
                        NATURALKEY,                     //业务主键
                        OperationType.INSERT,        //操作类型
                        Module.INPUT,                //模块
                        OPERATOR,                       //操作人
                        LogDirectory.INPUT,             //日志目录
                        Status.SUCCESS,              //状态
                        COUNT,                          //数量
                        REQUEST,                        //请求数据
                        RESPONSE,                       //响应数据
                        LOGINFO,                        //日志信息
                        RUNTIME                         //运行时间
                );
                LOGGER.info(logTemplate.toString());
                return RESPONSE;

            } catch (Exception e) {

                LOGERROR = e.toString();
                RESPONSE = InterfaceResponse.responseError(NATURALKEY, LOGERROR);
                //插入错误日志
                logTemplate = LogTemplate.SetLogTemplate(
                        NATURALKEY,                     //业务主键
                        OperationType.INSERT,        //操作类型
                        Module.INPUT,                //模块
                        OPERATOR,                       //操作人
                        LogDirectory.INPUT,             //日志目录
                        Status.ERROR,                //日志状态
                        COUNT,                          //数量
                        REQUEST,                        //请求数据
                        RESPONSE,                       //响应数据
                        LOGINFO,                        //日志信息
                        RUNTIME                         //运行时间
                );
                LOGGER.error(logTemplate.toString());
                return RESPONSE;

            } finally {
                //插入日志到数据库
                logService.insertLogInfo(logTemplate);
            }
        }
        return RESPONSE;
    }
}





