package com.ml.timi.model.entity;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 用户中间表(UserTestClient)实体类
 *
 * @author Lin
 * @since 2021-08-30 16:48:40
 */

@Data
@Component
public class UserTestClient implements Serializable {

    private static final long serialVersionUID = 902741181872372496L;


    private Long id;
    /** 昵称 */
    private String name;
    /** 密码 */
    private String pwd;
    /** 头像 */
    private String headImg;
    /** 手机号 */
    private String phone;
    /** 创建时间 */
    private LocalDateTime createTime;
    /** 登陆账号 */
    private String login;
    /** 状态 */
    private String status;
    /** 信息 */
    private String message;
    /** 业务主键 */
    private String naturalkey;
    /**
     *  用户订单集合
     *  这里注意 videoOrderList的名字
     *  为转JSON的时候用的
     *
     */
    public List<VideoOrderTestClient> videoOrderList;

}

