package com.ml.timi.mapper;

import com.ml.timi.utils.LogTemplate;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * ClassName:      LogMapper
 * Description:    日志插入数据库
 * Date:           2021 2021/7/8 14:36
 * Author:         Lin
 * Copyright:      Lin
 */
@Mapper
public interface LogMapper {

    /**
     * Method               insertLogInfo
     * Description          单条数据日志插入数据库
     * Author               Lin
     * Date                 2021/7/19 20:45
     * Version              1.0.0
     * @param           logTemplate 单条数据日志模板
     * @return int          返回插入数量
     */
    int insertLogInfo(LogTemplate logTemplate);

    /**
     * Method               insertLogInfoList
     * Description          批量数据日志插入数据库
     * Author               Lin
     * Date                 2021/7/19 20:45
     * Version              1.0.0
     * @param           logTemplateList 批量数据日志模板
     * @return int             返回插入数量
     */
    int insertLogInfoList(List<LogTemplate> logTemplateList);

}
