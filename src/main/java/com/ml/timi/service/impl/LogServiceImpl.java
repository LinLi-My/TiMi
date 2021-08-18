package com.ml.timi.service.impl;

import com.ml.timi.mapper.LogMapper;
import com.ml.timi.mapper.log.RequestTemplateMapper;
import com.ml.timi.model.log.request.RequestTemplate;
import com.ml.timi.service.LogService;
import com.ml.timi.utils.LogTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * ClassName:      LogServiceImpl
 * Description:    日志插入数据库
 * Date:           2021 2021/7/8 14:36
 * Author:         Lin
 * Copyright:      Lin
 */
@Service
public class LogServiceImpl implements LogService {



    @Resource
    private LogMapper logMapper;

    @Resource
    private RequestTemplateMapper requestTemplateMapper;


    /**
     * 新增数据
     *
     * @param requestTemplate 实例对象
     * @return 实例对象
     */
    @Override
    public RequestTemplate insertRequestTemplate(RequestTemplate requestTemplate) {
        requestTemplateMapper.insertRequestTemplate(requestTemplate);
        return requestTemplate;
    }

    int count = 0;
    int groupSize = 50;


    /**
     * Method               insertLogInfo
     * Description          单条数据日志插入数据库
     * Author               Lin
     * Date                 2021/7/19 20:45
     * Version              1.0.0
     * @param           logTemplate 单条数据日志模板
     * @return int          返回插入数量
     */
    @Override
    public int insertLogInfo(LogTemplate logTemplate) {
        return logMapper.insertLogInfo(logTemplate);
    }


    /**
     * Method               insertLogInfoList
     * Description          批量数据日志插入数据库
     * Author               Lin
     * Date                 2021/7/19 20:45
     * Version              1.0.0
     * @param           logTemplateList 批量数据日志模板
     * @return int             返回插入数量
     */
    @Override
    public int insertLogInfoList(List<LogTemplate> logTemplateList) {


        int groupCount =  logTemplateList.size() / groupSize;

        if (logTemplateList.size() <= groupSize) {
            count = logMapper.insertLogInfoList(logTemplateList);
        } else {
            List subList = new ArrayList();
            for (int i = 0; i < groupCount; i++) {
                subList = logTemplateList.subList(0, groupSize);
                count = count + logMapper.insertLogInfoList(subList);
                logTemplateList.subList(0, groupSize).clear();
            }
            if (logTemplateList.size() > 0) {
                count = count + logMapper.insertLogInfoList(logTemplateList);
            }
        }
        return count;


    }


}



