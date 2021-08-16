package com.ml.timi.controller;

import com.ml.timi.config.enums.ResultEnum;
import com.ml.timi.model.entity.VideoBanner;
import com.ml.timi.service.VideoBannerService;
import com.ml.timi.utils.JsonData;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;


/**
 * ClassName:      VideoBannerController
 * Description:    视频轮播图
 * Date:           2021 2021/7/8 14:36
 * Author:         Lin
 * Copyright:      Lin
 */
@RestController
@RequestMapping("api/v1/pub/videobanner")
public class VideoBannerController {

    @Resource
    private VideoBannerService videoBannerService;



    /**
     * @Description:    方法描述
     *                  <>
     * @Author          Lin
     * @Date            2021/7/8 14:39
     * @Param           参数
     * @Return          java.lang.Object
     * @Version         1.0.0
     */
    @GetMapping("list")
    public Object videoBannerList() {


        List<VideoBanner> videoBannerList = videoBannerService.videoBannerList();


        return JsonData.resultData(ResultEnum.SELECT_SUCCESS,videoBannerList);
    }
}

