/**
 * Description:
 * Copyright (C), 2021 2021/6/24 15:18
 * Author:        Lin
 * History:       <author>          <time>          <version>          <desc>
 */


package com.ml.timi.controller;

import com.ml.timi.config.enums.ResultEnum;
import com.ml.timi.model.entity.Video;
import com.ml.timi.service.VideoService;
import com.ml.timi.utils.JsonData;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("api/v1/pub/video")
public class VideoController {

    @Resource
    private VideoService videoService;

    /**
     * 查询所有视频列表
     * @return
     */
    @GetMapping("list")
    public JsonData videoList(){

        List<Video> videoList = videoService.videoList();
       // return videoService.videoList();
        return JsonData.resultData(ResultEnum.SELECT_SUCCESS,videoList);
    }


    /**
     * 根据id查询视频详细信息
     * @return
     */
    @GetMapping("findDetailById")
    public JsonData findDetailById(@RequestParam(value = "video_id",required = true) int videoId){

        Video video = videoService.findDetailById(videoId);

        return JsonData.resultData(ResultEnum.SELECT_SUCCESS,video);
    }

}
