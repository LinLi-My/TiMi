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
    /** ????????????1 */
    private String batchId;
    /** ???????????? */
    private String status;
    /** ???????????? */
    private String message;
    /** ???????????? */
    private String statusBody;
    /** ???????????? */
    private String messageBody;
    /** ?????????????????? */
    private String naturalkeyBody;
    /** ???????????? */
    private int responseCount = 0;
    /** ?????????????????? */
    private int responseSuccessCount = 0;
    /** ?????????????????? */
    private int responseErrorCount = 0;
    /** Json???????????? */
    private Gson gson = new GsonBuilder()
            .setPrettyPrinting()                                                    //??????????????????json
            .serializeNulls()                                                       //???NULL?????????????????????
            .disableHtmlEscaping()                                                  //??????????????????
            .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter())   //?????????????????????????????????????????????????????????????????????Adapter?????????JsonSerializer??????JsonDeserializer???????????????[LocalDateTime?????????]
            .registerTypeAdapter(JsonElement.class, new LocalDateTimeAdapter())     //????????????LocalDateTime(String??????>LocalDateTime)?????????
            .create();

    @Override
    public String registerList(String requestData, String requestMD5) throws Exception {

        List<UserTestClient> requestBodyList;
        List<ResponseBody> responseBodyList = new ArrayList();
        List<VideoOrderTestClient> videoOrderTestClientList = new ArrayList<>();
        /** ?????????????????? */
        JsonData jsonData = CommonUtils.CheckInterfaceParam(requestData, requestMD5);
        Integer Code = jsonData.getCode();
        String Msg = jsonData.getMsg();
        Object Data = jsonData.getData();

        if (jsonData.getCode() != 3000) {
            String responseError = "????????????" + Code + "\n" +
                    "?????????" + Msg + "\n" +
                    "?????????" + Data;
            throw new Exception(responseError);
        }

        /** ????????????3000???????????????????????? */
        /** ???????????? Json ????????????????????? RequestTemplate */
        requestTemplate = (RequestTemplate) jsonData.getData();
        batchId = requestTemplate.getBatchId();
        /** ??????????????????????????? */
        LOGGER.info(LogType.Insert_Request_Log_Template + requestTemplate.toString());
        /** ??? requestTemplate ???????????????????????? */
        requestTemplateService.insert(requestTemplate);
        /** ???????????????Json??????????????? List<UserTestClient> */
        String requestBodyJson = requestTemplate.getRequestBody();
        requestBodyList = gson.fromJson(requestBodyJson,
                new TypeToken<List<UserTestClient>>() {
                }.getType());


        SqlSession sqlSession = sqlSessionFactory.openSession(false);
        userTestClientMapper = sqlSession.getMapper(UserTestClientMapper.class);
        for (UserTestClient requestBody : requestBodyList) {
            try {

                /** ???requestBodyList?????????????????????????????? */
                userTestClientMapper.insert(requestBody);
                /**
                 * ??????????????????public List<VideoOrderTestClient> videoOrderList;
                 */
                videoOrderTestClientList = requestBody.getVideoOrderList();
                if (ObjectUtils.isNotEmpty(videoOrderTestClientList)) {


                    /** ???videoOrder???????????????????????? */
                    videoOrderTestClientMapper.insertBatch(videoOrderTestClientList);

                }
                LOGGER.info(LogType.Insert_Request_Log_Body + requestBody.toString());
                /** ?????????????????? */
                naturalkeyBody = requestBody.getNaturalkey();
                messageBody = Message.BODY_SUCCESS;
                statusBody = Status.BODY_SUCCESS;
                responseBody = new ResponseBody.ResponseBodyBuilder()
                        .setNaturalkey(naturalkeyBody)
                        .setMessage(messageBody)
                        .setStatus(statusBody)
                        .build();
                responseBodyList.add(responseBody);
                /** ???????????? Success ?????? */
                LOGGER.info(LogType.Insert_Response_Log_Body + responseBody.toString());

                responseSuccessCount++;
                //??????NATURALKEY????????????????????????
            } catch (Exception e) {
                sqlSession.rollback();
                /**
                 * ?????? Error ??????????????????
                 */
                naturalkeyBody = requestBody.getNaturalkey();
                messageBody = Message.BODY_ERROR;
                statusBody = Status.BODY_ERROR;
                String exceptionMessage = ExpertionLin.Infor(e);
                responseBody = new ResponseBody.ResponseBodyBuilder()
                        .setNaturalkey(naturalkeyBody)
                        .setMessage(messageBody + exceptionMessage)
                        .setStatus(statusBody)
                        .build();

                responseBodyList.add(responseBody);
                /** ???????????? Error ?????? */
                LOGGER.error(LogType.Insert_Response_Log_Body + responseBody.toString());

                responseErrorCount++;
            } finally {
                /** ??????naturalkey?????????????????? */
                userTestClientMapper.updateStatusByNaturalkey(responseBody);
            }

        }
        sqlSession.close();
        responseCount = responseErrorCount + responseSuccessCount;
        /** ???????????????????????????????????? */

        //????????????
        if (responseErrorCount > 0 && responseSuccessCount > 0) {
            status = Status.PART_ERROR;
            message = Message.PART_ERROR;
        }
        //????????????
        if (responseErrorCount > 0 && responseSuccessCount < 0) {
            status = Status.ERROR;
            message = Message.ERROR;
        }
        //????????????
        if (responseErrorCount == 0) {
            status = Status.SUCCESS;
            message = Message.SUCCESS;
        }

        /** ?????????????????? */
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

        LOGGER.info(LogType.Insert_Response_Log_Template + responseTemplateJSON);
        /** ?????? batchId ???????????? Error ????????? */
        responseTemplateService.update(responseTemplate);

        return responseTemplateJSON;


    }


}





