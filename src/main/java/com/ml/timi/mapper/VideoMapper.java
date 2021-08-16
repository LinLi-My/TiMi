package com.ml.timi.mapper;

import com.ml.timi.model.entity.Video;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


@Mapper

public interface VideoMapper {

    /**
     * 查询所有视频列表
     * @return
     */
    List<Video> videoList();

    /**
     * 根据ID查询视频
     * @return
     * @param videoId
     */
    Video findDetailById(int videoId);
}
