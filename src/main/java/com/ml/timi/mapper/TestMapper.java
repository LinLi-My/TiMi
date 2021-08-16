package com.ml.timi.mapper;

import com.ml.timi.model.entity.User;
import com.ml.timi.utils.LogTemplate;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TestMapper {



    User findUserById(User user);

    int insertLogInfo(LogTemplate logTemplate);

    int insertLogInfoList(List<LogTemplate> logTemplateList);

    int TestinsertForeach(List<LogTemplate> logTemplateList);
}
