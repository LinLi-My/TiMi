package com.ml.timi.service;

import com.ml.timi.model.log.request.RequestTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 请求头日志(RequestTemplate)表服务接口
 *
 * @author Lin
 * @since 2021-08-27 09:34:24
 */
@Service
public interface RequestTemplateService {
    /**
     * 通过实体作为筛选条件查询
     * 查询条件为空时，则默认查询全部
     *
     * @param requestTemplate RequestTemplate对象
     * @return RequestTemplate集合
     */
    List<RequestTemplate> search(RequestTemplate requestTemplate);

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    RequestTemplate searchById(Integer id);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<RequestTemplate> searchAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param requestTemplate 实例对象
     * @return 实例对象
     */
    int insert(RequestTemplate requestTemplate);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param  requestTemplateList RequestTemplate对象集合
     * @return 影响行数
     */
    int insertBatch(List<RequestTemplate> requestTemplateList);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param  requestTemplateList RequestTemplate对象集合
     * @return 影响行数
     */
    int insertOrUpdateBatch(List<RequestTemplate> requestTemplateList);

    /**
     * 修改数据
     *
     * @param requestTemplate RequestTemplate对象
     * @return 实例对象
     */
    RequestTemplate update(RequestTemplate requestTemplate);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);

}
