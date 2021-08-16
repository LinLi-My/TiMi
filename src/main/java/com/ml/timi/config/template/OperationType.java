package com.ml.timi.config.template;
import lombok.Data;

/**
 * Description:
 * Copyright (C), 2021 2021/7/2 16:29
 * Author:        Lin
 * History:       <author>          <time>          <version>          <desc>
 */
@Data
public class OperationType {

    /**
     * 添加状态
     */
    public static final String INSERT = "添加";
    /**
     * 修改状态
     */
    public static final String UPDATE = "修改";
    /**
     * 删除状态
     */
    public static final String DELETE = "删除";


}
