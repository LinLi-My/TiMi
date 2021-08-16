/**
 * Description:
 * Copyright (C), 2021 2021/6/24 14:42
 * Author:        Lin
 * History:       <author>          <time>          <version>          <desc>
 */


package com.ml.timi.model.entity;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Episode {

    public  int id ;
    public  String title ;
    public  int num ;
    public  int ordered ;
    public  String playUrl ;
    public  int chapterId ;
    public  String free ;
    public  int videoId ;
    public LocalDateTime createTime ;

}
