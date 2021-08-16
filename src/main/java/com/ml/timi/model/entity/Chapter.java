

package com.ml.timi.model.entity;

import lombok.Data;

import java.util.List;

/**
 * Description:
 * Copyright (C), 2021 2021/6/24 14:39
 * Author:        Lin
 * History:       <author>          <time>          <version>          <desc>
 */
@Data
public class Chapter {

    public int id;
    public  int videoId ;
    public  String title ;
    public  String ordered ;
    public  String createTime ;
    public List<Episode> episodeList;

}
