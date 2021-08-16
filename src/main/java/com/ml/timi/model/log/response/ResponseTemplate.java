package com.ml.timi.model.log.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

/**
 * ClassName:
 * Description:
 * Date:           2021 2021/7/23 13:27
 * Author:         Lin
 * Copyright:      Lin
 */
@Component
@Data
public class ResponseTemplate {

    /** 自增主键 */
    private int id;

    /** 批次标识 */
    private String batchId;

    /** 模块 */
    private String module;

    /** 请求数据 */
    private List<ResponseBody> responseBody;

    /** 请求状态 */
    private String responseStatus;

    /** 请求状态信息 */
    private String responseStatusMessage;

    /** 请求时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime responseTime;

    /** 请求体数量 */
    private int responseBodyCount;

    /** 请求体成功数量 */
    private int responseBodySuccessCount;

    /** 请求体失败数量 */
    private int responseBodyErrorCount;

    /** 耗时 */
    private String elapsedTime;

    public ResponseTemplate() {
    }


    @Override
    public String toString() {

        return  "\n"+"【 请求数据日志"+
                "\n"+"批次标识：[" + batchId + "]    " +
                "模块：[" + module + "]    " +
                "请求时间：[" + responseTime + "]    " +
                "请求体数量：[" + responseBodyCount + "]    " +
                "elapsedTime：[" + elapsedTime + "]    " +
                "\n" + "请求状态：[" + responseStatus + "]    " +
                "请求状态信息：[" + responseStatusMessage + "]    " +
                "\n" + "请求数据：[" + responseBody + "]    "+
                "\n" + "】"
                ;

    }


    public static final class ResponseTemplateBuilder {
        private int id;
        private String batchId;
        private String module;
        private List<ResponseBody> responseBody;
        private String responseStatus;
        private String responseStatusMessage;
        private LocalDateTime responseTime;
        private int responseBodyCount;
        private int responseBodySuccessCount;
        private int responseBodyErrorCount;
        private String elapsedTime;

        public ResponseTemplateBuilder() {
        }

        public static ResponseTemplateBuilder aResponseTemplate() {
            return new ResponseTemplateBuilder();
        }

        public ResponseTemplateBuilder setId(int id) {
            this.id = id;
            return this;
        }

        public ResponseTemplateBuilder setBatchId(String batchId) {
            this.batchId = batchId;
            return this;
        }

        public ResponseTemplateBuilder setModule(String module) {
            this.module = module;
            return this;
        }

        public ResponseTemplateBuilder setResponseBody(List<ResponseBody> responseBody) {
            this.responseBody = responseBody;
            return this;
        }

        public ResponseTemplateBuilder setResponseStatus(String responseStatus) {
            this.responseStatus = responseStatus;
            return this;
        }

        public ResponseTemplateBuilder setResponseStatusMessage(String responseStatusMessage) {
            this.responseStatusMessage = responseStatusMessage;
            return this;
        }

        public ResponseTemplateBuilder setResponseTime(LocalDateTime responseTime) {
            this.responseTime = responseTime;
            return this;
        }

        public ResponseTemplateBuilder setResponseBodyCount(int responseBodyCount) {
            this.responseBodyCount = responseBodyCount;
            return this;
        }

        public ResponseTemplateBuilder setResponseBodySuccessCount(int responseBodySuccessCount) {
            this.responseBodySuccessCount = responseBodySuccessCount;
            return this;
        }

        public ResponseTemplateBuilder setResponseBodyErrorCount(int responseBodyErrorCount) {
            this.responseBodyErrorCount = responseBodyErrorCount;
            return this;
        }

        public ResponseTemplateBuilder setElapsedTime(String elapsedTime) {
            this.elapsedTime = elapsedTime;
            return this;
        }

        public ResponseTemplate build() {
            ResponseTemplate responseTemplate = new ResponseTemplate();
            responseTemplate.setId(id);
            responseTemplate.setBatchId(batchId);
            responseTemplate.setModule(module);
            responseTemplate.setResponseBody(responseBody);
            responseTemplate.setResponseStatus(responseStatus);
            responseTemplate.setResponseStatusMessage(responseStatusMessage);
            responseTemplate.setResponseTime(responseTime);
            responseTemplate.setResponseBodyCount(responseBodyCount);
            responseTemplate.setResponseBodySuccessCount(responseBodySuccessCount);
            responseTemplate.setResponseBodyErrorCount(responseBodyErrorCount);
            responseTemplate.setElapsedTime(elapsedTime);
            return responseTemplate;
        }
    }
}





