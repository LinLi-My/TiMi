package com.ml.timi.utils;

import com.ml.timi.model.entity.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;

/**
 * Description: JWT工具类
 * 注意点
 * 1、生成的token ，是可以通过base64进行解密出明文信息
 * 2、base64进行解密出明文信息，修改在进行编码，则会解密失败
 * 3、无法作废一颁布的token，查费改秘钥
 * Copyright (C), 2021 2021/6/25 16:12
 * Author:        Lin
 * History:       <author>          <time>          <version>          <desc>
 */
public class JWTUtils {

    /**
     * 过期时间，一周
     */
    private static final long EXPIRE = 60000 * 60 * 24 * 7;

    /**
     * 过期时间，一周
     */
    private static final String ISSUER = "Lin";

    /**
     * 加密秘钥
     */
    private static final String SECRET = "TiMi.ML";

    /**
     * 令牌前缀
     */
    private static final String TOKEN_PREFIX = "TiMi";

    /**
     * 主题
     */
    private static final String SUBJECT = "TiMi";


    /**
     * 根据用户信息，生成令牌
     * WT格式组成 头部、负载、签名
     * <p>
     * header+payload+signature
     * 头部：主要是描述签名算法
     * 负载：主要描述是加密对象的信息，如用户的id等，也可以加些规范里面的东西，如iss签发者，EXPIRE 过期时间，SUBJECT 面向的用户
     * 签名：主要是把前面两部分进行加密，防止别人拿到token进行base解密后篡改token
     *
     * @param user 用户注册信息
     * @return  token 令牌
     */
    public static String geneJsonWebToken(User user) {

        String token = Jwts.builder().setSubject(SUBJECT)
                .claim("head_img", user.getHeadImg())
                .claim("id", user.getId())
                .claim("name", user.getName())
                .setIssuedAt(new Date()) //令牌下发时间
                .setIssuer(ISSUER) //签发者
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRE)) //设置令牌过期时间
                .signWith(SignatureAlgorithm.HS256, SECRET).compact(); //签名，加密方式

        token = TOKEN_PREFIX + token;

        return token;
    }


    /**
     * 校验token的方法
     * @param token 令牌
     * @return  claims
     */
    public static Claims checkJWT(String token) {

        try {

            final Claims claims = Jwts.parser().setSigningKey(SECRET)
                    //.requireIssuer(ISSUER)
                    .parseClaimsJws(token.replace(TOKEN_PREFIX, ""))
                    .getBody();

            return claims;

        } catch (Exception e) {
            return null;
        }

    }
}
