/**
 * Description:
 * Copyright (C), 2021 2021/6/24 15:37
 * Author:        Lin
 * History:       <author>          <time>          <version>          <desc>
 */


package com.ml.timi.utils;

import com.ml.timi.config.enums.ResultEnum;
import lombok.Data;

@Data
public class JsonData {


    /**
     * 状态码
     */
    private Integer code;

    /**
     * 状态信息
     */
    private String msg;

    /**
     * 业务数据
     */
    private Object data;



    public JsonData(){}

    public JsonData(Integer code, Object data, String msg){
        this.code = code;
        this.data = data;
        this.msg = msg;
    }

    public JsonData(ResultEnum resultEnum){
        this.code = resultEnum.getCode();
        this.msg = resultEnum.getMessage();
    }


    public JsonData(ResultEnum resultEnum,Object data){
        this.code = resultEnum.getCode();
        this.data = data;
        this.msg = resultEnum.getMessage();
    }

    /**
     * 返回结果
     * @return
     */
    public static JsonData resultData(ResultEnum resultEnum,Object date){

        return new JsonData(resultEnum,date);
    }

    /**
     * 返回结果
     * @return
     */
    public static JsonData resultData(ResultEnum resultEnum){

        return new JsonData(resultEnum);
    }






}
