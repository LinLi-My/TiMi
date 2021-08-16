/**
 * Description:
 * Copyright (C), 2021 2021/6/24 17:33
 * Author:        Lin
 * History:       <author>          <time>          <version>          <desc>
 */


package com.ml.timi.service.impl;

import com.ml.timi.mapper.TestMapper;
import com.ml.timi.model.entity.User;
import com.ml.timi.service.TestService;
import com.ml.timi.utils.LogTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service

public class TestServiceImpl implements TestService {


    @Resource
    private TestMapper testMapper;

    @Override
    public User findUserById(User user) {
        return testMapper.findUserById(user);
    }

    @Override
    public int insertLogInfo(LogTemplate logTemplate) {
        return testMapper.insertLogInfo(logTemplate);
    }


    @Override
    public int insertLogInfoList(List<LogTemplate> logTemplateList) {
        return testMapper.insertLogInfoList(logTemplateList);
    }

    @Override
    public int TestinsertForeach(List<LogTemplate> logTemplateList) {
        return testMapper.TestinsertForeach(logTemplateList);
    }


}
