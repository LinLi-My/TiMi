package com.ml.timi.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;

/**
 * ClassName:      JSONUtil
 * Description:    JSON与对象集合转换工具类
 * Date:           2021 2021/7/14 9:17
 * Author:         Lin
 * Copyright:      Lin
 */
public class JSONUtil {

    /** 定义jackson对象 */
    private static final ObjectMapper MAPPER = new ObjectMapper();

    /** 定义对象转换Json失败 */
    private static final String objectToJsonErrorMessage = "实体对象转换Json失败";

    /** 定义Json转化实体对象失败 */
    private static final String jsonToEntityErrorMessage = "Json转化实体对象失败";

    /** 定义Json转换集合失败 */
    private static final String jsonToListErrorMessage = "Json转换集合失败";


    /**
     * Method               objectToJson
     * Description          将JAVA对象转换成JSON字符串。
     * Author               Lin
     * Date                 2021/7/14 10:22
     * Version              1.0.0
     * @param               data
     * @return java.lang.String
     */
    public static String objectToJson(Object data) throws Exception {
        try {
            String string = MAPPER.writeValueAsString(data);
            return string;
        } catch (JsonProcessingException e) {
            throw new Exception(objectToJsonErrorMessage);
        }

    }


    /**
     * Method           jsonToEntity
     * Description      将JSON结果集转化为JAVA对象
     * Author           Lin
     * Date             2021/7/14 10:26
     * Version          1.0.0
     * @param           jsonData    json数据
     * @param           beanType    对象中的object类型
     * @return          T           返回对象中的object类型
     */
    public static <T> T jsonToEntity(String jsonData, Class<T> beanType) throws Exception {
        try {
            T t = MAPPER.readValue(jsonData, beanType);
            return t;
        } catch (Exception e) {
            throw new Exception(jsonToEntityErrorMessage);
        }

    }


    /**
     * Method           jsonToList
     * Description      将Json数据转换成JAVA对象的List
     * Author           Lin
     * Date             2021/7/14 10:30
     * Version          1.0.0
     * @param           jsonData    json数据
     * @param           beanType    对象中的object类型
     * @return          java.util.List<T>   返回泛型List集合
     */
    public static <T> List<T> jsonToList(String jsonData, Class<T> beanType) throws Exception {


        JavaType javaType = MAPPER.getTypeFactory().constructParametricType(List.class, beanType);
        try {
            List<T> list = MAPPER.readValue(jsonData, javaType);
            return list;
        } catch (Exception e) {
            throw new Exception(jsonToListErrorMessage);
        }

    }
    /*public static <T> List<T> getValue(String jsonData, Class<T> beanType) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(jsonData);
        //获取name字段值
        JsonNode name = jsonNode.get("name");
        String s = name.asText();
        System.out.println(s);
        //获取elements字段下数组第二个对象的age
        JsonNode elements = jsonNode.get("elements");
        JsonNode object2 = elements.get(1);//从0开始哦
        JsonNode age = object2.get("age");
        int i = age.asInt();
        System.out.println(i);
        JavaType javaType = MAPPER.getTypeFactory().constructParametricType(List.class, beanType);
        try {
            List<T> list = MAPPER.readValue(jsonData, javaType);
            return list;
        } catch (Exception e) {
            throw new Exception(jsonToListErrorMessage);
        }

    }*/





}
