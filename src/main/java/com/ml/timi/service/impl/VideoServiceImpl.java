/**
 * Description:
 * Copyright (C), 2021 2021/6/24 15:15
 * Author:        Lin
 * History:       <author>          <time>          <version>          <desc>
 */


package com.ml.timi.service.impl;

import com.ml.timi.model.entity.Video;
import com.ml.timi.mapper.VideoMapper;
import com.ml.timi.service.VideoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;


@Service
public class VideoServiceImpl implements VideoService {

    @Resource
    private VideoMapper videoMapper;

    @Override
    public List<Video> videoList() {
        return videoMapper.videoList();
    }

    @Override
    public Video findDetailById(int videoId) {


        return videoMapper.findDetailById(videoId);
    }
}
