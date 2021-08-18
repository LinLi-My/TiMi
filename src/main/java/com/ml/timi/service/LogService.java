package com.ml.timi.service;

import com.ml.timi.model.log.request.RequestTemplate;
import com.ml.timi.utils.LogTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface LogService {

    /**
     * Method               insertLogInfo
     * Description          单条数据日志插入数据库
     * Author               Lin
     * Date                 2021/7/19 20:45
     * Version              1.0.0
     * @param               logTemplate 单条数据日志模板
     * @return int          返回插入数量
     */
    int insertLogInfo(LogTemplate logTemplate);

    int insertLogInfoList(List<LogTemplate> logTemplateList);

    RequestTemplate insertRequestTemplate(RequestTemplate requestTemplate);
}
