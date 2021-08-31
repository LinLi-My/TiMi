package com.ml.timi.service.impl;

import com.ml.timi.mapper.log.ResponseBodyMapper;
import com.ml.timi.model.log.response.ResponseBody;
import com.ml.timi.service.ResponseBodyService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (ResponseBody)表服务实现类
 *
 * @author Lin
 * @since 2021-08-30 09:42:35
 */
@Service("responseBodyService")
public class ResponseBodyServiceImpl implements ResponseBodyService {
    @Resource
    private ResponseBodyMapper responseBodyMapper;

    /**
     * 通过实体作为筛选条件查询
     * 查询条件为空时，则默认查询全部
     *
     * @param responseBody ResponseBody对象
     * @return ResponseBody集合
     */
    @Override
    public List<ResponseBody> search(ResponseBody responseBody) {

        return this.responseBodyMapper.search(responseBody);
    }


    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public ResponseBody searchById(Integer id) {
        return this.responseBodyMapper.searchById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<ResponseBody> searchAllByLimit(int offset, int limit) {
        return this.responseBodyMapper.searchAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param responseBody 实例对象
     * @return 实例对象
     */
    @Override
    public int insert(ResponseBody responseBody) {
        return this.responseBodyMapper.insert(responseBody);

    }

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param  responseBodyList ResponseBody对象集合
     * @return 影响行数
     */
    @Override
    public int insertBatch(List<ResponseBody> responseBodyList) {
        return this.responseBodyMapper.insertBatch(responseBodyList);
    }

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param  responseBodyList ResponseBody对象集合
     * @return 影响行数
     */
    @Override
    public int insertOrUpdateBatch(List<ResponseBody> responseBodyList) {
        return this.responseBodyMapper.insertOrUpdateBatch(responseBodyList);
    }

    /**
     * 修改数据
     *
     * @param responseBody ResponseBody对象
     * @return 实例对象
     */
    @Override
    public ResponseBody update(ResponseBody responseBody) {
        this.responseBodyMapper.update(responseBody);
        return this.searchById(responseBody.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    @Override
    public int deleteById(Integer id) {
        return this.responseBodyMapper.deleteById(id);
    }
}
