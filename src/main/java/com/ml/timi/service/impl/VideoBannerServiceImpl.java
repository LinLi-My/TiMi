/**
 * Description:
 * Copyright (C), 2021 2021/6/24 15:15
 * Author:        Lin
 * History:       <author>          <time>          <version>          <desc>
 */


package com.ml.timi.service.impl;

import com.ml.timi.model.entity.VideoBanner;
import com.ml.timi.mapper.VideoBannerMapper;
import com.ml.timi.service.VideoBannerService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;


@Service
public class VideoBannerServiceImpl implements VideoBannerService {

    @Resource
    private VideoBannerMapper videoBannerMapper;

    @Override
    public List<VideoBanner> videoBannerList() {
        return videoBannerMapper.videoBannerList();
    }
}
