package com.ml.timi.service;

import com.ml.timi.model.entity.User;
import com.ml.timi.utils.LogTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public interface TestService {



    User findUserById(User user);

    int insertLogInfo(LogTemplate logTemplate);

    int insertLogInfoList(List<LogTemplate> logTemplateList );
    int TestinsertForeach(List<LogTemplate> logTemplateList);
}
