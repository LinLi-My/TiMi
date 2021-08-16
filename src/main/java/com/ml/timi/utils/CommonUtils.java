


package com.ml.timi.utils;

import com.ml.timi.config.enums.ResultEnum;
import com.ml.timi.model.entity.User;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.DigestUtils;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;


/**
 * Description:  公共方法类
 * Copyright (C), 2021 2021/6/25 15:30
 * Author:        Lin
 * History:       <author>          <time>          <version>          <desc>
 */
public class CommonUtils {

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
     * 对象 = null
     * @param obj
     * @return ture
     */
    public static boolean isNull(Object obj) {
        return obj == null;
    }

    /**
     * 对象 != null
     * @param obj
     * @return false
     */
    public static boolean isNotNull(Object obj) {
        return !isNull(obj);
    }

    /**
     * 对象 = null  、大小 = 0
     * @param obj
     * @return ture
     */
    public static boolean isEmpty(Object obj) {
        if (obj == null) return true;
        else if (obj instanceof CharSequence) return ((CharSequence) obj).length() == 0;
        else if (obj instanceof Collection) return ((Collection) obj).isEmpty();
        else if (obj instanceof Map) return ((Map) obj).isEmpty();
        else if (obj.getClass().isArray()) return Array.getLength(obj) == 0;

        return false;
    }

    public static boolean isNotEmpty(Object obj) {
        return !isEmpty(obj);
    }

    public static String isEmpty(String string) {
        return CommonUtils.isNotEmpty(string) == true ? string.trim() : "";
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
     * @return              com.ml.timi.utils.JsonData
     */
    public static JsonData CheckInterfaceParam(String requestData, String requestMD5){

        //1.判断接收的两个参数是否为空
        if (StringUtils.isNoneBlank(requestData,requestMD5)) {
            //2.对比MD5是否一致
            if (CommonUtils.MD5Equals(requestData, requestMD5)) {
                return JsonData.resultData(ResultEnum.VERIFICATION_PASSED);
            }
            return JsonData.resultData(ResultEnum.MD5_INEQUALITY);
        }
        return JsonData.resultData(ResultEnum.Null_Data);
    }


}
