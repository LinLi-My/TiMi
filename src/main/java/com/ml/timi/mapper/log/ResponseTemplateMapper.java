package com.ml.timi.mapper.log;

import com.ml.timi.model.log.response.ResponseTemplate;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 请求头日志(ResponseTemplate)表数据库访问层
 *
 * @author Lin
 * @since 2021-08-27 09:37:30
 */
@Mapper
public interface ResponseTemplateMapper {

    /**
     * 通过实体作为筛选条件查询
     * 查询条件为空时，则默认查询全部
     *
     * @param responseTemplate ResponseTemplate对象
     * @return ResponseTemplate集合
     */
    List<ResponseTemplate> search(ResponseTemplate responseTemplate);

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    ResponseTemplate searchById(Integer id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<ResponseTemplate> searchAllByLimit(@Param("offset") int offset, @Param("limit") int limit);

    /**
     * 新增数据
     *
     * @param responseTemplate ResponseTemplate对象
     * @return 影响行数
     */
    int insert(ResponseTemplate responseTemplate);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param  responseTemplateList ResponseTemplate对象集合
     * @return 影响行数
     */
    int insertBatch(List<ResponseTemplate> responseTemplateList);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param  responseTemplateList ResponseTemplate对象集合
     * @return 影响行数
     */
    int insertOrUpdateBatch(List<ResponseTemplate> responseTemplateList);

    /**
     * 修改数据
     *
     * @param responseTemplate ResponseTemplate对象
     * @return 影响行数
     */
    int update(ResponseTemplate responseTemplate);

    /**
     * 修改数据
     *
     * @param responseBodyMap String对象
     * @return 影响行数
     */
    int updateResponseBody(Map<String,String> responseBodyMap);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);

}

