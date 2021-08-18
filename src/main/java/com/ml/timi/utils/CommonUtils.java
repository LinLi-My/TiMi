


package com.ml.timi.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;
import com.ml.timi.config.enums.ResultEnum;
import com.ml.timi.model.entity.UserTest;
import com.ml.timi.model.log.request.RequestTemplate;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.DigestUtils;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


/**
 * Description:  公共方法类
 * Copyright (C), 2021 2021/6/25 15:30
 * Author:        Lin
 * History:       <author>          <time>          <version>          <desc>
 */
public class CommonUtils {
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    /**
     * Description: MD5加密
     * Copyright (C), 2021 2021/6/2 15:35
     * Author:        Lin
     * History:       <author>          <time>          <version>          <desc>
     */
    public static String MD5Encrypt(String param) {


        String md5Cipher = DigestUtils.md5DigestAsHex(param.getBytes());
        return md5Cipher;
    }

    /**
     * Description: 判断MD5是否一致
     * Copyright (C), 2021 2021/6/2 15:35
     * Author:        Lin
     * History:       <author>          <time>          <version>          <desc>
     */
    public static boolean MD5Equals(String requestParam, String md5) {

        String md5Cipher = DigestUtils.md5DigestAsHex(requestParam.getBytes());
        return md5.equals(md5Cipher);

    }

    /**
     * Method               CheckInterfaceParam
     * Description          校验请求数据
     *                      <>
     * Author               Lin
     * Date                 2021/7/20 19:26
     * Version              1.0.0
     * @param               requestData 接收的JSON字符串
     * @param               requestMD5  接收的MD值
     * @return com.ml.timi.utils.JsonData
     */
    public static JsonData CheckInterfaceParam(String requestData, String requestMD5) throws Exception {

        RequestTemplate requestTemplate = new RequestTemplate();
        List<UserTest> requestBodyList = new ArrayList();
        Gson gson = new GsonBuilder()
                .setPrettyPrinting() //格式化输出的json
                .serializeNulls()    //有NULL值是也进行解析
                .disableHtmlEscaping()  //解析特殊符号
                .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter())   ////为某特定对象设置固定的序列或反序列方式，自定义Adapter需实现JsonSerializer或者JsonDeserializer接口序列化[LocalDateTime的解析]
                .registerTypeAdapter(JsonElement.class, new LocalDateTimeAdapter())     //反序列化LocalDateTime(String——>LocalDateTime)的解析
                .create();


        //1.判断请求数据、MD5的两个参数是否为空
        if (StringUtils.isAnyBlank(requestData, requestMD5)) {
            return JsonData.resultData(ResultEnum.Null_RequestData);
        }
        //判断MD5值是否相同
        if (!CommonUtils.MD5Equals(requestData, requestMD5)) {
            return JsonData.resultData(ResultEnum.MD5_INEQUALITY);
        }
        try {
            //将请求的Json数据转换为对象RequestTemplate
            requestTemplate = gson.fromJson(requestData, RequestTemplate.class);


            //将请求体的Json数据转换为List<UserTest>
            requestBodyList = gson.fromJson(requestTemplate.getRequestBody(),
                    new TypeToken<List<UserTest>>() {
                    }.getType());
        } catch (Exception e) {
            String result = ResultEnum.Data_Parsing_Error.getMessage();
            String ExceptionMessage = ExpertionLin.Infor(e) + " " + result;
            new CommonUtils().LOGGER.error(ExceptionMessage);
            throw new Exception(ExceptionMessage);
        }
        //判断请求体是否为空
        if (ObjectUtils.isEmpty(requestBodyList)) {
            return JsonData.resultData(ResultEnum.Null_RequestDataBody);
        }
        return JsonData.resultData(ResultEnum.VERIFICATION_PASSED, requestTemplate);




















       /* //1.判断接收的两个参数是否为空
        if (StringUtils.isNoneBlank(requestData,requestMD5)) {
            //2.对比MD5是否一致
            if (CommonUtils.MD5Equals(requestData, requestMD5)) {
                return JsonData.resultData(ResultEnum.VERIFICATION_PASSED);
            }
            return JsonData.resultData(ResultEnum.MD5_INEQUALITY);
        }
        return JsonData.resultData(ResultEnum.Null_Data);*/
    }


}
