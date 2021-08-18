package com.ml.timi.mapper.log;


import com.ml.timi.model.log.response.ResponseTemplate;
import org.apache.ibatis.annotations.Mapper;

/**
 * 请求头日志(RequestTemplate)表数据库访问层
 *
 * @author LinLi
 * @since 2021-08-13 14:05:13
 */
@Mapper
public interface ResponseTemplateMapper {


    /**
     * 修改数据
     *
     * @param responseTemplateError 实例对象
     * @return 影响行数
     */
    int updateResponseTemplateError(ResponseTemplate responseTemplateError);

    /**
     * 新增数据
     *
     * @param responseBody 实例对象
     * @return 影响行数
     */
    // int insertResponseBody(List<ResponseBody> responseBody);

    /* *//**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     *//*
    RequestTemplate queryById(Integer id);

    *//**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     *//*
    List<RequestTemplate> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    *//**
     * 通过实体作为筛选条件查询
     *
     * @param requestTemplate 实例对象
     * @return 对象列表
     *//*
    List<RequestTemplate> queryAll(RequestTemplate requestTemplate);

    *//**
     * 新增数据
     *
     * @param requestTemplate 实例对象
     * @return 影响行数
     *//*
    int insert(RequestTemplate requestTemplate);

    *//**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<RequestTemplate> 实例对象列表
     * @return 影响行数
     *//*
    int insertBatch(@Param("entities") List<RequestTemplate> entities);

    *//**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<RequestTemplate> 实例对象列表
     * @return 影响行数
     *//*
    int insertOrUpdateBatch(@Param("entities") List<RequestTemplate> entities);

    *//**
     * 修改数据
     *
     * @param requestTemplate 实例对象
     * @return 影响行数
     *//*
    int update(RequestTemplate requestTemplate);

    *//**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     *//*
    int deleteById(Integer id);*/

}

