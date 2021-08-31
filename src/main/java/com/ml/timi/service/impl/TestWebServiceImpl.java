/**
 * ClassName:
 * Description:
 * Date:           2021 2021/7/19 15:51
 * Author:         Lin
 * Copyright:      Lin
 */


package com.ml.timi.service.impl;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;
import com.ml.timi.config.template.LogDirectory;
import com.ml.timi.config.template.Module;
import com.ml.timi.config.template.OperationType;
import com.ml.timi.config.template.Status;
import com.ml.timi.mapper.UserTestClientMapper;
import com.ml.timi.mapper.VideoOrderTestClientMapper;
import com.ml.timi.model.entity.User;
import com.ml.timi.model.entity.UserTestClient;
import com.ml.timi.model.entity.VideoOrderTestClient;
import com.ml.timi.model.log.request.RequestTemplate;
import com.ml.timi.model.log.response.ResponseBody;
import com.ml.timi.model.log.response.ResponseTemplate;
import com.ml.timi.service.RequestTemplateService;
import com.ml.timi.service.ResponseTemplateService;
import com.ml.timi.service.TestWebService;
import com.ml.timi.utils.*;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.jws.WebService;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static com.ml.timi.config.template.CommonVar.*;

@Service
@WebService(serviceName = "WebServiceTest",
        targetNamespace = "http://service.timi.ml.com",
        endpointInterface = "com.ml.timi.service.TestWebService")
public class TestWebServiceImpl implements TestWebService {

    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
    @Resource
    public InterfaceResponse interfaceResponse;
    @Resource
    public User user;
    @Resource
    RequestTemplateService requestTemplateService;
    @Resource
    ResponseTemplateService   responseTemplateService;
    @Resource
    UserTestClient userTestClient;
    @Resource
    UserTestClientMapper userTestClientMapper;
    @Resource
    VideoOrderTestClientMapper videoOrderTestClientMapper;
    @Resource
    ResponseBody responseBody;
    @Resource
    SqlSessionFactory sqlSessionFactory;
    Gson gson = new GsonBuilder()
            .setPrettyPrinting() //格式化输出的json
            .serializeNulls()    //有NULL值是也进行解析
            .disableHtmlEscaping()  //解析特殊符号
            .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter())   ////为某特定对象设置固定的序列或反序列方式，自定义Adapter需实现JsonSerializer或者JsonDeserializer接口序列化[LocalDateTime的解析]
            .registerTypeAdapter(JsonElement.class, new LocalDateTimeAdapter())     //反序列化LocalDateTime(String——>LocalDateTime)的解析
            .create();
    @Resource
    private LogTemplate logTemplate;

    @Override
    public String registerList(String requestData, String requestMD5) throws Exception {
        String request = "";
        RequestTemplate requestTemplate = new RequestTemplate();
        List<UserTestClient> requestBodyList = new ArrayList<>();
        List<ResponseBody> responseBodyList = new ArrayList();
        List<VideoOrderTestClient> videoOrderTestClientList = new ArrayList<>();
        /** 校验请求数据 */
        JsonData jsonData = CommonUtils.CheckInterfaceParam(requestData, requestMD5);
        Integer Code = jsonData.getCode();
        String Msg = jsonData.getMsg();
        Object Data = jsonData.getData();
        String responseError = "状态码：" + Code + "\n" +
                "信息：" + Msg + "\n" +
                "数据：" + Data;


        if (jsonData.getCode() != 3000) {
            throw new Exception(responseError);
        }
        /** 状态码：3000，则校验数据通过 */
        /** 将请求的Json数据转换为对象RequestTemplate */
        requestTemplate = (RequestTemplate) jsonData.getData();
        String BATCHID = requestTemplate.getBatchId();
        /** 将请求数据插入日志 */
        LOGGER.info(requestTemplate.toString());
        /** 将requestTemplate数据存储到数据库 */
        requestTemplateService.insert(requestTemplate);
        /** 将请求体的Json数据转换为 List<UserTest> */
        String requestBodyJson = requestTemplate.getRequestBody();
        requestBodyList = gson.fromJson(requestBodyJson,
                new TypeToken<List<UserTestClient>>() {
                }.getType());


        SqlSession sqlSession = sqlSessionFactory.openSession(false);
        userTestClientMapper = sqlSession.getMapper(UserTestClientMapper.class);

        String Message = "";
        for (UserTestClient requestBody : requestBodyList) {
            try {
                /** 将requestBodyList数据存储到数据库 */
                userTestClientMapper.insert(requestBody);
                /**
                 * 这里注意实体public List<VideoOrderTestClient> videoOrderList;
                 */
                videoOrderTestClientList = requestBody.getVideoOrderList();
                if (ObjectUtils.isNotEmpty(videoOrderTestClientList)) {

                        /** 将videoOrder数据存储到数据库 */
                        videoOrderTestClientMapper.insertBatch(videoOrderTestClientList);

                }
                LOGGER.info(requestBody.toString());
                //组装返回报文
                String a = "数据成功+";
                Message = a;
                String NATURALKEY = requestBody.getNaturalkey();
                String STATUS = "Success";
                 responseBody = new ResponseBody.ResponseBodyBuilder()
                        .setNaturalkey(NATURALKEY)
                        .setMessage(Message)
                        .setStatus(STATUS)
                        .build();
                responseBodyList.add(responseBody);
                /** 记录响应 Success 日志 */
                LOGGER.info(responseBody.toString());

                //根据NATURALKEY修改成功下传状态
            } catch (Exception e) {
                /**
                 * 响应 Error 处理响应数据
                 */
                String a = "数据插入失败+";
                Message = a + ExpertionLin.Infor(e);
                String NATURALKEY = requestBody.getNaturalkey();
                String STATUS = "ERROR";
                 responseBody = new ResponseBody.ResponseBodyBuilder()
                        .setNaturalkey(NATURALKEY)
                        .setMessage(Message)
                        .setStatus(STATUS)
                        .build();

                responseBodyList.add(responseBody);
                /** 记录响应 Error 日志 */
                LOGGER.error(responseBody.toString());

                sqlSession.rollback();
                //根据NATURALKEY修改失败下传状态


            } finally {
                userTestClientMapper.updateStatusByNaturalkey(responseBody);

            }

        }
        /** 关闭流 */
        sqlSession.close();

        /** 组装响应数据 */
        ResponseTemplate responseTemplate = new ResponseTemplate.ResponseTemplateBuilder()
                .setBatchId(BATCHID)
                .setModule(Module.INPUT)
                .setResponseTime(LocalDateTime.now())
                .setResponseStatus(Status.ERROR)
                .setResponseStatusMessage(Message)
                .setResponseBody(responseBodyList)
                .build();
        String responseTemplateJSON = gson.toJson(responseTemplate);
        LOGGER.info(responseTemplateJSON);
        /** 根据 batchId 更新响应 Error 的数据 */

          responseTemplateService.update(responseTemplate);
        return responseTemplateJSON;


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
                // COUNT = userMapper.register(user);
                LocalDateTime endLocalDateTime = LocalDateTime.now();
                //操作成功后 处理响应数据
                RESPONSE = InterfaceResponse.responseSuccess(NATURALKEY);
                RUNTIME = DateTool.TimeCalculation(startLocalDateTime, DateTool.UNIT_HOURS);
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

            }
        }
        return RESPONSE;
    }
}





