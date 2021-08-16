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
public class ResponseBody {

    /** 自增主键 */
    private int naturalId;

    /** 批次标识 */
    private String batchId;

    /** 模块 */
    private String module;

    /** 状态 */
    private String status;

    /** 状态信息 */
    private String massage;

    /** code */
    private String code;

    /** 请求时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime responseTime;

    public ResponseBody() {
    }


    @Override
    public String toString() {

        return  "\n"+"【 响应数据日志"+
                "\n"+"批次标识：[" + batchId + "]    " +
                "模块：[" + module + "]    " +
                "响应时间：[" + responseTime + "]    " +
                "状态：[" + status + "]    " +
                "code：[" + code + "]    " +
                "\n" + "状态信息：[" + massage + "]    "
                ;

    }


    public static final class ResponseBodyBuilder {
        private int naturalId;
        private String batchId;
        private String module;
        private String status;
        private String massage;
        private String code;
        private LocalDateTime responseTime;

        private ResponseBodyBuilder() {
        }

        public static ResponseBodyBuilder aResponseBody() {
            return new ResponseBodyBuilder();
        }

        public ResponseBodyBuilder setNaturalId(int naturalId) {
            this.naturalId = naturalId;
            return this;
        }

        public ResponseBodyBuilder setBatchId(String batchId) {
            this.batchId = batchId;
            return this;
        }

        public ResponseBodyBuilder setModule(String module) {
            this.module = module;
            return this;
        }

        public ResponseBodyBuilder setStatus(String status) {
            this.status = status;
            return this;
        }

        public ResponseBodyBuilder setMassage(String massage) {
            this.massage = massage;
            return this;
        }

        public ResponseBodyBuilder setCode(String code) {
            this.code = code;
            return this;
        }

        public ResponseBodyBuilder setResponseTime(LocalDateTime responseTime) {
            this.responseTime = responseTime;
            return this;
        }

        public ResponseBody build() {
            ResponseBody responseBody = new ResponseBody();
            responseBody.setNaturalId(naturalId);
            responseBody.setBatchId(batchId);
            responseBody.setModule(module);
            responseBody.setStatus(status);
            responseBody.setMassage(massage);
            responseBody.setCode(code);
            responseBody.setResponseTime(responseTime);
            return responseBody;
        }
    }
}





