package com.ml.timi.service.impl;

import com.ml.timi.mapper.log.RequestTemplateMapper;
import com.ml.timi.model.log.request.RequestTemplate;
import com.ml.timi.service.RequestTemplateService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 请求头日志(RequestTemplate)表服务实现类
 *
 * @author Lin
 * @since 2021-08-27 09:34:24
 */
@Service("requestTemplateService")
public class RequestTemplateServiceImpl implements RequestTemplateService {
    @Resource
    private RequestTemplateMapper requestTemplateMapper;

    /**
     * 通过实体作为筛选条件查询
     * 查询条件为空时，则默认查询全部
     *
     * @param requestTemplate RequestTemplate对象
     * @return RequestTemplate集合
     */
    @Override
    public List<RequestTemplate> search(RequestTemplate requestTemplate) {

        return this.requestTemplateMapper.search(requestTemplate);
    }


    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public RequestTemplate searchById(Integer id) {
        return this.requestTemplateMapper.searchById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<RequestTemplate> searchAllByLimit(int offset, int limit) {
        return this.requestTemplateMapper.searchAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param requestTemplate 实例对象
     * @return 实例对象
     */
    @Override
    public int insert(RequestTemplate requestTemplate) {
        return this.requestTemplateMapper.insert(requestTemplate);

    }

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param  requestTemplateList RequestTemplate对象集合
     * @return 影响行数
     */
    @Override
    public int insertBatch(List<RequestTemplate> requestTemplateList) {
        return this.requestTemplateMapper.insertBatch(requestTemplateList);
    }

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param  requestTemplateList RequestTemplate对象集合
     * @return 影响行数
     */
    @Override
    public int insertOrUpdateBatch(List<RequestTemplate> requestTemplateList) {
        return this.requestTemplateMapper.insertOrUpdateBatch(requestTemplateList);
    }

    /**
     * 修改数据
     *
     * @param requestTemplate RequestTemplate对象
     * @return 实例对象
     */
    @Override
    public RequestTemplate update(RequestTemplate requestTemplate) {
        this.requestTemplateMapper.update(requestTemplate);
        return this.searchById(requestTemplate.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    @Override
    public int deleteById(Integer id) {
        return this.requestTemplateMapper.deleteById(id);
    }
}
