package com.ml.timi.service.impl;

import com.ml.timi.model.entity.VideoOrderTestClient;
import com.ml.timi.mapper.VideoOrderTestClientMapper;
import com.ml.timi.service.VideoOrderTestClientService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 视频订单表——测试(VideoOrderTestClient)表服务实现类
 *
 * @author Lin
 * @since 2021-08-30 16:49:17
 */
@Service("videoOrderTestClientService")
public class VideoOrderTestClientServiceImpl implements VideoOrderTestClientService {
    @Resource
    private VideoOrderTestClientMapper videoOrderTestClientMapper;

    /**
     * 通过实体作为筛选条件查询
     * 查询条件为空时，则默认查询全部
     *
     * @param videoOrderTestClient VideoOrderTestClient对象
     * @return VideoOrderTestClient集合
     */
    @Override
    public List<VideoOrderTestClient> search(VideoOrderTestClient videoOrderTestClient) {

        return this.videoOrderTestClientMapper.search(videoOrderTestClient);
    }


    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public VideoOrderTestClient searchById(Integer id) {
        return this.videoOrderTestClientMapper.searchById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<VideoOrderTestClient> searchAllByLimit(int offset, int limit) {
        return this.videoOrderTestClientMapper.searchAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param videoOrderTestClient 实例对象
     * @return 实例对象
     */
    @Override
    public int insert(VideoOrderTestClient videoOrderTestClient) {
        return this.videoOrderTestClientMapper.insert(videoOrderTestClient);

    }

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param  videoOrderTestClientList VideoOrderTestClient对象集合
     * @return 影响行数
     */
    @Override
    public int insertBatch(List<VideoOrderTestClient> videoOrderTestClientList) {
        return this.videoOrderTestClientMapper.insertBatch(videoOrderTestClientList);
    }

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param  videoOrderTestClientList VideoOrderTestClient对象集合
     * @return 影响行数
     */
    @Override
    public int insertOrUpdateBatch(List<VideoOrderTestClient> videoOrderTestClientList) {
        return this.videoOrderTestClientMapper.insertOrUpdateBatch(videoOrderTestClientList);
    }

    /**
     * 修改数据
     *
     * @param videoOrderTestClient VideoOrderTestClient对象
     * @return 实例对象
     */
    @Override
    public VideoOrderTestClient update(VideoOrderTestClient videoOrderTestClient) {
        this.videoOrderTestClientMapper.update(videoOrderTestClient);
        return this.searchById(videoOrderTestClient.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    @Override
    public int deleteById(Integer id) {
        return this.videoOrderTestClientMapper.deleteById(id);
    }
}
