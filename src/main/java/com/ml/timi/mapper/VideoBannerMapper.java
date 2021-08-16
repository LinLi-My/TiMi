package com.ml.timi.mapper;

import com.ml.timi.model.entity.VideoBanner;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

@Mapper
public interface VideoBannerMapper {
    List<VideoBanner> videoBannerList();
}
