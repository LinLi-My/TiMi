/**
 * ClassName:
 * Description:
 * Date:           2021 2021/7/23 10:58
 * Author:         Lin
 * Copyright:      Lin
 */


package com.ml.timi.model.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class ResponseTemplateHeader {
    /** 批次标识 */
    String batchId = "Batch";

    /** 回传时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    LocalDateTime postBackTime;

    /** 回传状态 */
    String postBackStatus;

    /** 回传数量 */
    int postBackCount;

    /** 回传成功数量 */
    int postBackSuccessCount;

    /** 回传失败数量 */
    int postBackErrorCount;

    List<Object> objectList ;


    public ResponseTemplateHeader() {

    }

    public ResponseTemplateHeader(String batchId, String postBackStatus, int postBackCount, int postBackSuccessCount, int postBackErrorCount, List<Object> objectList) {
        this.batchId = batchId;
        this.postBackTime = LocalDateTime.now();
        this.postBackStatus = postBackStatus;
        this.postBackCount = postBackCount;
        this.postBackSuccessCount = postBackSuccessCount;
        this.postBackErrorCount = postBackErrorCount;
        this.objectList = objectList;
    }
}
