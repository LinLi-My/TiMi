/**
 * ClassName:
 * Description:
 * Date:           2021 2021/7/20 13:23
 * Author:         Lin
 * Copyright:      Lin
 */


package com.ml.timi.utils;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.ml.timi.config.enums.ResultEnum;
import com.ml.timi.config.template.Status;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@Data
public class InterfaceResponse {

    /** 主键 */
    public String naturalKey;
    /** 状态 */
    public String status;
    /** 信息 */
    public String msg;
    /** 时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    public LocalDateTime time;

    public InterfaceResponse() {
    }

    public InterfaceResponse(String naturalKey) {
        this.time = LocalDateTime.now();
        this.naturalKey = naturalKey;
        this.status = Status.SUCCESS;
        this.msg = "请求成功";
    }

    public InterfaceResponse(String naturalKey, String msg) {
        this.time = LocalDateTime.now();
        this.naturalKey = naturalKey;
        this.status = Status.ERROR;
        this.msg = msg;
    }

    /**
     * 成功响应结果封装
     * @return
     */
    public static String responseSuccess(String naturalKey) {
        return JSONUtil.objectToJson(
                JsonData.resultData(ResultEnum.INSERT_SUCCESS, new InterfaceResponse(naturalKey))
        );
    }

    /**
     * 失败响应结果封装
     * @return
     */
    public static String responseError(String naturalKey, String msg) {
        return JSONUtil.objectToJson(
                JsonData.resultData(ResultEnum.INSERT_ERROR, new InterfaceResponse(naturalKey, msg))
        );
    }
}
