/**
 * Description:
 * Copyright (C), 2021 2021/6/12 23:46
 * Author:        Lin
 * History:       <author>          <time>          <version>          <desc>
 */


package com.ml.timi.model.entity;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
@Data
public class Video {
    /**
     * 主键
     */
    public int id;

    /**
     * 视频标题
     */
    public String title;

    /**
     * 视频详情
     */
    public String summary;

    /**
     * 视频详情
     */
    public String coverImg;

    /**
     * 价格
     */
    public Double price;



    /**
     * 评分
     */
    public Double point;

    /**
     * 创建时间
     */
    public LocalDateTime createTime;

    /**
     * 视频章节
     */
    public List<Chapter> chapterList;
}
