package com.ml.timi.service.impl;

import com.ml.timi.mapper.log.ResponseTemplateMapper;
import com.ml.timi.model.log.response.ResponseTemplate;
import com.ml.timi.service.ResponseTemplateService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * 请求头日志(ResponseTemplate)表服务实现类
 *
 * @author Lin
 * @since 2021-08-27 09:37:31
 */
@Service("responseTemplateService")
public class ResponseTemplateServiceImpl implements ResponseTemplateService {
    @Resource
    private ResponseTemplateMapper responseTemplateMapper;

    /**
     * 通过实体作为筛选条件查询
     * 查询条件为空时，则默认查询全部
     *
     * @param responseTemplate ResponseTemplate对象
     * @return ResponseTemplate集合
     */
    @Override
    public List<ResponseTemplate> search(ResponseTemplate responseTemplate) {

        return this.responseTemplateMapper.search(responseTemplate);
    }


    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public ResponseTemplate searchById(Integer id) {
        return this.responseTemplateMapper.searchById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<ResponseTemplate> searchAllByLimit(int offset, int limit) {
        return this.responseTemplateMapper.searchAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param responseTemplate 实例对象
     * @return 实例对象
     */
    @Override
    public int insert(ResponseTemplate responseTemplate) {
        return this.responseTemplateMapper.insert(responseTemplate);

    }

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param  responseTemplateList ResponseTemplate对象集合
     * @return 影响行数
     */
    @Override
    public int insertBatch(List<ResponseTemplate> responseTemplateList) {
        return this.responseTemplateMapper.insertBatch(responseTemplateList);
    }

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param  responseTemplateList ResponseTemplate对象集合
     * @return 影响行数
     */
    @Override
    public int insertOrUpdateBatch(List<ResponseTemplate> responseTemplateList) {
        return this.responseTemplateMapper.insertOrUpdateBatch(responseTemplateList);
    }

    /**
     * 修改数据
     *
     * @param responseTemplate ResponseTemplate对象
     * @return 实例对象
     */
    @Override
    public int update(ResponseTemplate responseTemplate) {

        return  this.responseTemplateMapper.update(responseTemplate);
    }

    /**
     * 修改数据
     *
     * @param responseBodyMap String对象
     * @return 实例对象
     */
    @Override
    public int updateResponseBody(Map<String,String> responseBodyMap) {
        return  this.responseTemplateMapper.updateResponseBody(responseBodyMap);
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    @Override
    public int deleteById(Integer id) {
        return this.responseTemplateMapper.deleteById(id);
    }
}
