package com.ml.timi.model.log.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * ClassName:
 * Description:
 * Date:           2021 2021/7/23 13:27
 * Author:         Lin
 * Copyright:      Lin
 */
@Component
@Data
public class RequestTemplate {

    /** 自增主键 */
    private int id;

    /** 批次标识 */
    private String batchId;

    /** 模块 */
    private String module;

    /** 请求数据 */
    private String requestBody;

    /** 请求状态 */
    private String requestStatus;

    /** 请求状态信息 */
    private String requestStatusMessage;

    /** 请求时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime requestTime;

    /** 请求体数量 */
    private int requestBodyCount;

    /** 耗时 */
    private String elapsedTime;

    public RequestTemplate() {
    }


    public RequestTemplate(String batchId, String module, String requestBody, String requestStatus, String requestStatusMessage, int requestBodyCount, String elapsedTime) {
        this.batchId = batchId;
        this.module = module;
        this.requestBody = requestBody;
        this.requestStatus = requestStatus;
        this.requestStatusMessage = requestStatusMessage;
        this.requestTime = LocalDateTime.now();
        this.requestBodyCount = requestBodyCount;
        this.elapsedTime = elapsedTime;
    }

    public static final class RequestTemplateBuilder {
        private int id;
        private String batchId;
        private String module;
        private String requestBody;
        private String requestStatus;
        private String requestStatusMessage;
        private LocalDateTime requestTime;
        private int requestBodyCount;
        private String elapsedTime;

        public RequestTemplateBuilder() {
        }

        public static RequestTemplateBuilder aRequestTemplate() {
            return new RequestTemplateBuilder();
        }

        public RequestTemplateBuilder setId(int id) {
            this.id = id;
            return this;
        }

        public RequestTemplateBuilder setBatchId(String batchId) {
            this.batchId = batchId;
            return this;
        }

        public RequestTemplateBuilder setModule(String module) {
            this.module = module;
            return this;
        }

        public RequestTemplateBuilder setRequestBody(String requestBody) {
            this.requestBody = requestBody;
            return this;
        }

        public RequestTemplateBuilder setRequestStatus(String requestStatus) {
            this.requestStatus = requestStatus;
            return this;
        }

        public RequestTemplateBuilder setRequestStatusMessage(String requestStatusMessage) {
            this.requestStatusMessage = requestStatusMessage;
            return this;
        }

        public RequestTemplateBuilder setRequestTime(LocalDateTime requestTime) {
            this.requestTime = requestTime;
            return this;
        }

        public RequestTemplateBuilder setRequestBodyCount(int requestBodyCount) {
            this.requestBodyCount = requestBodyCount;
            return this;
        }

        public RequestTemplateBuilder setElapsedTime(String elapsedTime) {
            this.elapsedTime = elapsedTime;
            return this;
        }

        public RequestTemplate build() {
            RequestTemplate requestTemplate = new RequestTemplate();
            requestTemplate.setId(id);
            requestTemplate.setBatchId(batchId);
            requestTemplate.setModule(module);
            requestTemplate.setRequestBody(requestBody);
            requestTemplate.setRequestStatus(requestStatus);
            requestTemplate.setRequestStatusMessage(requestStatusMessage);
            requestTemplate.setRequestTime(requestTime);
            requestTemplate.setRequestBodyCount(requestBodyCount);
            requestTemplate.setElapsedTime(elapsedTime);
            return requestTemplate;
        }
    }


    @Override
    public String toString() {

        return  "\n"+"【 请求数据日志"+
                "\n"+"批次标识：[" + batchId + "]    " +
                "模块：[" + module + "]    " +
                "请求时间：[" + requestTime + "]    " +
                "请求体数量：[" + requestBodyCount + "]    " +
                "elapsedTime：[" + elapsedTime + "]    " +
                "\n" + "请求状态：[" + requestStatus + "]    " +
                "请求状态信息：[" + requestStatusMessage + "]    " +
                "\n" + "请求数据：[" + requestBody + "]    "+
                "\n" + "】"
                ;

    }


}





