package com.ml.timi.service;

import com.ml.timi.model.entity.VideoBanner;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface VideoBannerService {
    List<VideoBanner> videoBannerList();
}
