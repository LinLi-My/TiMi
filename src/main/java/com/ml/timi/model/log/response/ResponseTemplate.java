/**
 * ClassName:
 * Description:
 * Date:           2021 2021/7/23 10:58
 * Author:         Lin
 * Copyright:      Lin
 */


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

@Component
@Data
public class ResponseTemplate {

    /** 主键 */
    private int id;
    /** 批次标识 */
    private String batchId;
    /** 模块 */
    private String module;
    /** 响应时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    LocalDateTime responseTime;

    /** 响应状态 */
    String responseStatus;

    /** 响应状态信息 */
    String responseStatusMessage;

    /** 响应数量 */
    int responseCount;

    /** 响应成功数量 */
    int responseSuccessCount;

    /** 响应失败数量 */
    int responseErrorCount;

    /**
     * 响应体-响应数据
     * 业务主键 ：naturalKey
     * 状态：status
     * 信息：message
     */
    List<ResponseBody> responseBody;

    public ResponseTemplate() {
    }


    @Override
    public String toString() {

        return "\n" + "【 接口调用失败日志" +
                "\n" + "批次标识：[" + batchId + "]    " +
                "模块：[" + module + "]    " +
                "响应时间：[" + responseTime + "]    " +
                "\n" + "响应状态：[" + responseStatus + "]    " +
                "响应状态信息：[" + responseStatusMessage + "]    " +
                "\n" + "】"
                ;
    }


    public static final class ResponseTemplateBuilder {
        LocalDateTime responseTime;
        String responseStatus;
        String responseStatusMessage;
        int responseCount;
        int responseSuccessCount;
        int responseErrorCount;
        List<ResponseBody> responseBody;
        private int id;
        private String batchId;
        private String module;

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

        public ResponseTemplateBuilder setResponseTime(LocalDateTime responseTime) {
            this.responseTime = responseTime;
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

        public ResponseTemplateBuilder setResponseCount(int responseCount) {
            this.responseCount = responseCount;
            return this;
        }

        public ResponseTemplateBuilder setResponseSuccessCount(int responseSuccessCount) {
            this.responseSuccessCount = responseSuccessCount;
            return this;
        }

        public ResponseTemplateBuilder setResponseErrorCount(int responseErrorCount) {
            this.responseErrorCount = responseErrorCount;
            return this;
        }

        public ResponseTemplateBuilder setResponseBody(List<ResponseBody> responseBody) {
            this.responseBody = responseBody;
            return this;
        }

        public ResponseTemplate build() {
            ResponseTemplate responseTemplate = new ResponseTemplate();
            responseTemplate.setId(id);
            responseTemplate.setBatchId(batchId);
            responseTemplate.setModule(module);
            responseTemplate.setResponseTime(responseTime);
            responseTemplate.setResponseStatus(responseStatus);
            responseTemplate.setResponseStatusMessage(responseStatusMessage);
            responseTemplate.setResponseCount(responseCount);
            responseTemplate.setResponseSuccessCount(responseSuccessCount);
            responseTemplate.setResponseErrorCount(responseErrorCount);
            responseTemplate.setResponseBody(responseBody);
            return responseTemplate;
        }
    }
}
