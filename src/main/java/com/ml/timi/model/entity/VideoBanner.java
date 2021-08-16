/**
 * Description:
 * Copyright (C), 2021 2021/6/24 14:37
 * Author:        Lin
 * History:       <author>          <time>          <version>          <desc>
 */


package com.ml.timi.model.entity;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class VideoBanner {
    /**
     * 主键
     */
    public int id;

    /**
     * 地址
     */
    public String url;

    /**
     * 图片
     */
    public String img;

    /**
     * 创建时间
     */
    public LocalDateTime createTime;

    /**
     * 权重（数字越小越靠前）
     */
    public int weight;


}
