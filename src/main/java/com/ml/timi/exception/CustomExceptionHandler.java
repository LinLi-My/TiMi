/*
package com.ml.timi.exception;


import com.ml.timi.utils.JsonData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

*/
/**
 * Description:
 * Copyright (C), 2021 2021/6/24 17:01
 * Author:        Lin
 * History:       <author>          <time>          <version>          <desc>
 *//*

@ControllerAdvice
public class CustomExceptionHandler {

    private final static Logger logger = LoggerFactory.getLogger(CustomExceptionHandler.class);

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public JsonData handle(Exception e) {

        logger.error("[ 系统异常 ]{}", e);

        if (e instanceof TiMiException) {

            TiMiException tiMiException = (TiMiException) e;

            return JsonData.buildError(tiMiException.getCode(), tiMiException.getMsg());

        } else {

            return JsonData.buildError("全局异常，未知错误");

        }


    }
}


*/
