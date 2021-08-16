package com.ml.timi.utils;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;


/**
 * ClassName:      LogTemplate
 * Description:    日志模板
 * Date:           2021 2021/7/8 14:36
 * Author:         Lin
 * Copyright:      Lin
 */
@Component
@Data
public class LogTemplate {
    /**
     * 业务主键
     */
    String naturalKey;

    /**
     * 操作时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    LocalDateTime operationTime;

    /**
     * 模块
     */
    String logModule;

    /**
     * 操作类型
     */
    String logOperationType;

    /**
     * 操作人
     */
    String operator;

    /**
     * 日志信息
     */
    String logInfo;

    /**
     * 请求数据
     */
    String request;

    /**
     * 响应数据
     */
    String response;

    /**
     * 日志目录
     */
    String logDirectory;

    /**
     * IP地址
     */
    String ipAddress;

    /**
     * 数量
     */
    int count;

    /**
     * 状态
     * SUCCESS：成功
     * ERROR: 失败
     */
    String status;

    /**
     * 运行时间
     */
    String runtime;


    public LogTemplate() {
    }


    public LogTemplate(String naturalKey, String logOperationType, String logModule, String operator, String logDirectory, String status, int count, String request, String response, String logInfo, String runtime) {
        this.logModule = logModule;
        this.logOperationType = logOperationType;
        this.operator = operator;
        this.logInfo = logInfo;
        this.logDirectory = logDirectory;
        this.ipAddress = IpAddress.getIpAddress();
        this.operationTime = LocalDateTime.now();
        this.naturalKey = naturalKey;
        this.status = status;
        this.count = count;
        this.request = request;
        this.response = response;
        this.runtime = runtime;

    }



    @Override
    public String toString() {
        return "业务主键：[" + naturalKey + "]    " +
                "IP地址：[" + ipAddress + "]    " +
                "操作类型：[" + logOperationType + "]   " +
                "模块：[" + logModule + "]   " +
                "操作时间：[" + operationTime + "]   " +
                "操作人：[" + operator + "]   " +
                "日志目录：[" + logDirectory + "]   " +
                "状态：[" + status + "]   " +
                "数量：[" + count + "]   " +
                "运行时间：[" + runtime + "]   " +
                "\n" + "请求数据：[" + request + "]   " +
                "\n" + "响应数据：[" + response + "]   " +
                "\n" + "日志信息：[" + logInfo + "]   ";

    }

    /**
     * Method                  SetLogTemplate
     * Description             设置日志模板
     * Author                  Lin
     * Date                    2021/7/8 15:23
     * Version                 1.0.0
     *
     * @param naturalKey       业务主键
     * @param logOperationType 操作类型
     * @param logModule        模块
     * @param operator         操作人
     * @param logDirectory     日志目录
     * @param status           状态
     * @param count            数量
     * @param logInfo          日志信息
     * @return com.ml.timi.model.log.LogTemplate   返回 LogTemplate 对象
     */
    public static LogTemplate SetLogTemplate(String naturalKey, String logOperationType, String logModule,  String operator, String logDirectory, String status, int count,String request,String response, String logInfo, String runtime) {


        return new LogTemplate(naturalKey, logOperationType, logModule, operator, logDirectory, status, count,request, response,logInfo,runtime);
    }

}
