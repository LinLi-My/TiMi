package com.ml.timi.service;

import com.ml.timi.model.log.response.ResponseBody;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * (ResponseBody)表服务接口
 *
 * @author Lin
 * @since 2021-08-30 09:42:35
 */
@Service
public interface ResponseBodyService {
    /**
     * 通过实体作为筛选条件查询
     * 查询条件为空时，则默认查询全部
     *
     * @param responseBody ResponseBody对象
     * @return ResponseBody集合
     */
    List<ResponseBody> search(ResponseBody responseBody);

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    ResponseBody searchById(Integer id);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<ResponseBody> searchAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param responseBody 实例对象
     * @return 实例对象
     */
    int insert(ResponseBody responseBody);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param  responseBodyList ResponseBody对象集合
     * @return 影响行数
     */
    int insertBatch(List<ResponseBody> responseBodyList);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param  responseBodyList ResponseBody对象集合
     * @return 影响行数
     */
    int insertOrUpdateBatch(List<ResponseBody> responseBodyList);

    /**
     * 修改数据
     *
     * @param responseBody ResponseBody对象
     * @return 实例对象
     */
    ResponseBody update(ResponseBody responseBody);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);

}
