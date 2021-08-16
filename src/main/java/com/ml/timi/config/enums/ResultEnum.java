package com.ml.timi.config.enums;

import lombok.Getter;

/**
 * Description:  返回操作码枚举
 * Copyright (C), 2021 2021/6/25 14:36
 * Author:        Lin
 * History:       <author>          <time>          <version>          <desc>
 */

@Getter
public enum ResultEnum {



    /**
     *   验证通过
     */
    VERIFICATION_PASSED(3000, "验证通过"),

    /**
     *   数据为空
     */
    Null_Data(-3001, "数据为空"),

    /**
     *   MD5不一致
     */
    MD5_INEQUALITY(-3002, "MD5不一致"),

    /**
     *   未授权
     */
    UNAUTHORIZED(-1, "未授权"),


    /**
     *   未登录
     */
    NOT_LOGIN(-2, "未登录"),


    /**
     *   登录成功
     */
    LOGIN_SUCCESS(1000, "登录成功"),

    /**
     *   登陆失败
     *   用户名或密码错误
     */
    LOGIN_ERROR(-1000, "用户名或密码错误"),

    /**
     *   账号已被锁定
     */
    ACCOUNT_LOCKOUT(-1001, "账号已被锁定"),

    /**
     *   增加成功
     */
    INSERT_SUCCESS(2000, "增加成功"),

    /**
     *   增加失败
     */
    INSERT_ERROR(-2000, "增加失败"),

    /**
     *   查询成功
     */
    SELECT_SUCCESS(2001, "查询成功"),

    /**
     *   查询失败
     */
    SELECT_ERROR(-2001, "查询失败"),

    /**
     *   修改成功
     */
    UPDATE_SUCCESS(2002, "修改成功"),

    /**
     *   修改失败
     */
    UPDATE_ERROR(-2002, "修改失败"),

    /**
     *   删除成功
     */
    DELETE_SUCCESS(2003, "删除成功"),

    /**
     *   删除失败
     */
    DELETE_ERROR(-2003, "删除失败"),

    /**
     *   未查询到信息
     */
    RECORD_IS_NULL(2004, "未查询到信息"),

    /**
     *   保存信息失败
     */
    SAVE_FAIL(2005, "保存信息失败"),;

    /**
     *   状态码
     */
    private Integer code;

    /**
     *  状态信息
     */
    private String message;


    ResultEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

}
