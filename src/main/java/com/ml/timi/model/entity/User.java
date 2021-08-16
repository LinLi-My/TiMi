package com.ml.timi.model.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.List;


@Component
@Data
public class User {

    /** 数据库自增长主键 */
    public int id;

    /** 登陆账号 */
    public String login;

    /** 登陆密码 */
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)//仅限写入权限
    //@JsonProperty(access = JsonProperty.Access.READ_ONLY)//仅限读取权限
    public String pwd;

    /** 用户名 */
    public String name;

    /** 头像 */
    public String headImg;

    /** 电话号码 */
    public String phone;

    /** 创建时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    public LocalDateTime createTime;

    /** 用户订单集合 */
    public List<VideoOrder> videoOrderList;

}
