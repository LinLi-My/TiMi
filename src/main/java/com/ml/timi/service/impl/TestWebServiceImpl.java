package com.ml.timi.service.impl;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;
import com.ml.timi.config.template.LogType;
import com.ml.timi.config.template.Message;
import com.ml.timi.config.template.Module;
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
import com.ml.timi.utils.CommonUtils;
import com.ml.timi.utils.ExpertionLin;
import com.ml.timi.utils.JsonData;
import com.ml.timi.utils.LocalDateTimeAdapter;
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

@Service
@WebService(serviceName = "WebServiceTest",
        targetNamespace = "http://service.timi.ml.com",
        endpointInterface = "com.ml.timi.service.TestWebService")
public class TestWebServiceImpl implements TestWebService {

    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
    /** 响应数量 */
    int responseCount = 0;
    /** 响应成功数量 */
    int responseSuccessCount = 0;
    /** 响应失败数量 */
    int responseErrorCount = 0;
    @Resource
    private User user;
    @Resource
    private RequestTemplateService requestTemplateService;
    @Resource
    private ResponseTemplateService responseTemplateService;
    @Resource
    private UserTestClientMapper userTestClientMapper;
    @Resource
    private VideoOrderTestClientMapper videoOrderTestClientMapper;
    @Resource
    private RequestTemplate requestTemplate;
    @Resource
    private ResponseBody responseBody;
    @Resource
    private ResponseTemplate responseTemplate;
    @Resource
    private SqlSessionFactory sqlSessionFactory;
    /** 批次标识 */
    private String batchId;
    /** 响应状态 */
    private String status;
    /** 响应消息 */
    private String message;
    /** 响应状态 */
    private String statusBody;
    /** 响应消息 */
    private String messageBody;
    /** 响应业务主键 */
    private String naturalkeyBody;
    private Gson gson = new GsonBuilder()
            .setPrettyPrinting()                                                    //格式化输出的json
            .serializeNulls()                                                       //有NULL值是也进行解析
            .disableHtmlEscaping()                                                  //解析特殊符号
            .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter())   //为某特定对象设置固定的序列或反序列方式，自定义Adapter需实现JsonSerializer或者JsonDeserializer接口序列化[LocalDateTime的解析]
            .registerTypeAdapter(JsonElement.class, new LocalDateTimeAdapter())     //反序列化LocalDateTime(String——>LocalDateTime)的解析
            .create();

    @Override
    public String registerList(String requestData, String requestMD5) throws Exception {

        List<UserTestClient> requestBodyList = new ArrayList<>();
        List<ResponseBody> responseBodyList = new ArrayList();
        List<VideoOrderTestClient> videoOrderTestClientList = new ArrayList<>();
        /** 校验请求数据 */
        JsonData jsonData = CommonUtils.CheckInterfaceParam(requestData, requestMD5);
        Integer Code = jsonData.getCode();
        String Msg = jsonData.getMsg();
        Object Data = jsonData.getData();

        if (jsonData.getCode() != 3000) {
            String responseError = "状态码：" + Code + "\n" +
                    "信息：" + Msg + "\n" +
                    "数据：" + Data;
            throw new Exception(responseError);
        }
        /** 状态码：3000，则校验数据通过 */
        /** 将请求的 Json 数据转换为对象 RequestTemplate */
        requestTemplate = (RequestTemplate) jsonData.getData();
        batchId = requestTemplate.getBatchId();
        /** 将请求数据插入日志 */
        LOGGER.info(LogType.Insert_Request_Log_Template+requestTemplate.toString());
        /** 将 requestTemplate 数据存储到数据库 */
        requestTemplateService.insert(requestTemplate);
        /** 将请求体的Json数据转换为 List<UserTestClient> */
        String requestBodyJson = requestTemplate.getRequestBody();
        requestBodyList = gson.fromJson(requestBodyJson,
                new TypeToken<List<UserTestClient>>() {
                }.getType());


        SqlSession sqlSession = sqlSessionFactory.openSession(false);
        userTestClientMapper = sqlSession.getMapper(UserTestClientMapper.class);
        for (UserTestClient requestBody : requestBodyList) {
            try {

                /** 将requestBodyList数据遍历存储到数据库 */
                userTestClientMapper.insert(requestBody);
                /**
                 * 这里注意实体public List<VideoOrderTestClient> videoOrderList;
                 */
                videoOrderTestClientList = requestBody.getVideoOrderList();
                if (ObjectUtils.isNotEmpty(videoOrderTestClientList)) {

                   int i=1/0;
                    /** 将videoOrder数据存储到数据库 */
                    videoOrderTestClientMapper.insertBatch(videoOrderTestClientList);

                }
                LOGGER.info(LogType.Insert_Request_Log_Body+requestBody.toString());
                /** 组装返回报文 */
                naturalkeyBody = requestBody.getNaturalkey();
                messageBody = Message.BODY_SUCCESS;
                statusBody = Status.BODY_SUCCESS;
                responseBody = new ResponseBody.ResponseBodyBuilder()
                        .setNaturalkey(naturalkeyBody)
                        .setMessage(messageBody)
                        .setStatus(statusBody)
                        .build();
                responseBodyList.add(responseBody);
                /** 记录响应 Success 日志 */
                LOGGER.info(LogType.Insert_Response_Log_Body+responseBody.toString());

                responseSuccessCount++;
                //根据NATURALKEY修改成功下传状态
            } catch (Exception e) {
                sqlSession.rollback();
                /**
                 * 响应 Error 处理响应数据
                 */
                naturalkeyBody = requestBody.getNaturalkey();
                messageBody = Message.BODY_ERROR;
                statusBody = Status.BODY_ERROR;
                String exceptionMessage = ExpertionLin.Infor(e);
                responseBody = new ResponseBody.ResponseBodyBuilder()
                        .setNaturalkey(naturalkeyBody)
                        .setMessage(messageBody+exceptionMessage)
                        .setStatus(statusBody)
                        .build();

                responseBodyList.add(responseBody);
                /** 记录响应 Error 日志 */
                LOGGER.error(LogType.Insert_Response_Log_Body+responseBody.toString());

                responseErrorCount++;
            } finally {
                /** 根据naturalkey修改响应状态 */
                userTestClientMapper.updateStatusByNaturalkey(responseBody);
            }

        }
        sqlSession.close();
        responseCount = responseErrorCount + responseSuccessCount;
        /** 过滤集合里主键为空的集合 */

        //部分失败
        if (responseErrorCount > 0 && responseSuccessCount >0) {
            status = Status.PART_ERROR;
            message = Message.PART_ERROR;
        }
        //全部失败
        if(responseErrorCount > 0 && responseSuccessCount <0){
            status = Status.ERROR;
            message = Message.ERROR;
        }
        //全部成功
        if(responseErrorCount == 0 ){
            status = Status.SUCCESS;
            message = Message.SUCCESS;
        }

        /** 组装响应数据 */
        responseTemplate = new ResponseTemplate.ResponseTemplateBuilder()
                .setBatchId(batchId)
                .setModule(Module.INPUT)
                .setResponseTime(LocalDateTime.now())
                .setResponseStatus(status)
                .setResponseStatusMessage(message)
                .setResponseCount(responseCount)
                .setResponseSuccessCount(responseSuccessCount)
                .setResponseErrorCount(responseErrorCount)
                .setResponseBody(responseBodyList)
                .build();

        String responseTemplateJSON = gson.toJson(responseTemplate);

        LOGGER.info(LogType.Insert_Response_Log_Template+responseTemplateJSON);
        /** 根据 batchId 更新响应 Error 的数据 */
        responseTemplateService.update(responseTemplate);

        return responseTemplateJSON;


    }


}





