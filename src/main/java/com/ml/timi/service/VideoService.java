/**
 * Description:
 * Copyright (C), 2021 2021/6/24 15:13
 * Author:        Lin
 * History:       <author>          <time>          <version>          <desc>
 */


package com.ml.timi.service;

import com.ml.timi.model.entity.Video;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface VideoService {

    /**
     * @Description: 查询所有视频列表
     * 〈〉
     * @Author Lin
     * @Date 2021/6/24 15:12
     * @Param null
     * @Return 返回一个Video集合
     * @Version 1.0.0
     */
    List<Video> videoList();

    /**
     * 根据id查询视频详细信息
     *
     * @return
     * @param videoId
     */
    Video findDetailById(int videoId);
}
