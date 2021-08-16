package com.ml.timi.model.request;

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
public class RequestTemplateHeader {

    /** 批次标识 */
    String batchId ;

    /** 模块 */
    String module;

    /** 请求数据 */
    List requestDate;

    /** MD5 */
    String MD5;

    /** 下传状态 */
    String downloadStatus;

    /** 下传时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    LocalDateTime downloadTime;

    /** 下传数量 */
    int downloadCount;

    /** 耗时 */
    String elapsedTime;

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

    public RequestTemplateHeader() {
    }

        public RequestTemplateHeader(String batchId, String module, List requestDate, String downloadStatus, int downloadCount, String elapsedTime) {
        this.batchId = batchId;
        this.module = module;
        this.requestDate = requestDate;
        this.downloadStatus = downloadStatus;
        this.downloadTime = LocalDateTime.now();
        this.downloadCount = downloadCount;
        this.elapsedTime = elapsedTime;
    }

    public RequestTemplateHeader(String batchId, LocalDateTime postBackTime, String postBackStatus, int postBackCount, int postBackSuccessCount, int postBackErrorCount) {
        this.batchId = batchId;
        this.postBackTime = postBackTime;
        this.postBackStatus = postBackStatus;
        this.postBackCount = postBackCount;
        this.postBackSuccessCount = postBackSuccessCount;
        this.postBackErrorCount = postBackErrorCount;
    }

    /**
     * Method               SetLogMiner
     * Description          记录下传批次的日志
     * Author               Lin
     * Date                 2021/7/23 15:25
     * Version              1.0.0
     * @param           batchId
     * @param           module
     * @param           requestDate
     * @param           downloadStatus
     * @param           downloadCount
     * @param           elapsedTime
     * @return          com.ml.timi.utils.LogMiner
     */
    public static RequestTemplateHeader SetLogMiner(String batchId, String module, List requestDate, String downloadStatus, int downloadCount, String elapsedTime){

        return new RequestTemplateHeader(batchId,module,requestDate,downloadStatus,downloadCount, elapsedTime);
    }

    /**
     * Method               SetBackLogMiner
     * Description          记录回传日志
     * Author               Lin
     * Date                 2021/7/23 15:33
     * Version              1.0.0
     * @param           batchId
     * @param           postBackTime
     * @param           postBackStatus
     * @param           postBackCount
     * @param           postBackSuccessCount
     * @param           postBackErrorCount
     * @return          com.ml.timi.utils.LogMiner
     */
    public static RequestTemplateHeader SetBackLogMiner(String batchId, LocalDateTime postBackTime, String postBackStatus, int postBackCount, int postBackSuccessCount, int postBackErrorCount){


        return new RequestTemplateHeader(batchId,postBackTime,postBackStatus,postBackCount,postBackSuccessCount,postBackErrorCount);
    }


}
