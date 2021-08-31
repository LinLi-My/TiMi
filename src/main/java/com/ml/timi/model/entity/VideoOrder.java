/**
 * Description:
 * Copyright (C), 2021 2021/6/15 14:03
 * Author:        Lin
 * History:       <author>          <time>          <version>          <desc>
 */


package com.ml.timi.model.entity;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
@Component
@Data
public class VideoOrder {


    public int id;
    public String outTradeNo;
    public int state;
    public LocalDateTime createTime;
    public double totalFee;
    public int videoId;
    public String videoTitle;
    public String videoImg;
    public int userId;
    private User user;


}
