package com.ml.timi.mapper;

import com.ml.timi.model.entity.VideoOrderTestClient;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 视频订单表——测试(VideoOrderTestClient)表数据库访问层
 *
 * @author Lin
 * @since 2021-08-30 16:49:16
 */
@Mapper
public interface VideoOrderTestClientMapper {

    /**
     * 通过实体作为筛选条件查询
     * 查询条件为空时，则默认查询全部
     *
     * @param videoOrderTestClient VideoOrderTestClient对象
     * @return VideoOrderTestClient集合
     */
    List<VideoOrderTestClient> search(VideoOrderTestClient videoOrderTestClient);

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    VideoOrderTestClient searchById(Integer id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<VideoOrderTestClient> searchAllByLimit(@Param("offset") int offset, @Param("limit") int limit);

    /**
     * 新增数据
     *
     * @param videoOrderTestClient VideoOrderTestClient对象
     * @return 影响行数
     */
    int insert(VideoOrderTestClient videoOrderTestClient);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param  videoOrderTestClientList VideoOrderTestClient对象集合
     * @return 影响行数
     */
    int insertBatch(List<VideoOrderTestClient> videoOrderTestClientList);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param  videoOrderTestClientList VideoOrderTestClient对象集合
     * @return 影响行数
     */
    int insertOrUpdateBatch(List<VideoOrderTestClient> videoOrderTestClientList);

    /**
     * 修改数据
     *
     * @param videoOrderTestClient VideoOrderTestClient对象
     * @return 影响行数
     */
    int update(VideoOrderTestClient videoOrderTestClient);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);

}

