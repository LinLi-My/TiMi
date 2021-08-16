package com.ml.timi.service.impl;

import com.ml.timi.config.template.LogDirectory;
import com.ml.timi.config.template.Module;
import com.ml.timi.config.template.OperationType;
import com.ml.timi.config.template.Status;
import com.ml.timi.model.entity.User;
import com.ml.timi.mapper.UserMapper;
import com.ml.timi.service.LogService;
import com.ml.timi.service.UserService;
import com.ml.timi.utils.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.Random;

/**
 * ClassName:      UserService
 * Description:    用户实现
 * Date:           2021 2021/7/8 14:36
 * Author:         Lin
 * Copyright:      Lin
 */
@Service
public class UserServiceImpl implements UserService {
    @Resource
    private LogTemplate logTemplate;
    @Resource
    private LogService logService;
    @Resource
    public UserMapper userMapper;
    @Resource
    public User user;

    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    String naturalKey = "";
    String operator = "系统管理员";

    int count = -1;
    String request = "Lin";
    String response = "Lin";
    String logInfo = "Lin";
    String logError = "Lin";
    String runtime = "Lin";
    /**
     * 放在CND上的随机头像
     */
    private static final String[] headImg = {
            "https://xd-video-pc-img.oss-cn-beijing.aliyuncs.com/xdclass_pro/default/head_img/12.jpeg",
            "https://xd-video-pc-img.oss-cn-beijing.aliyuncs.com/xdclass_pro/default/head_img/11.jpeg",
            "https://xd-video-pc-img.oss-cn-beijing.aliyuncs.com/xdclass_pro/default/head_img/13.jpeg",
            "https://xd-video-pc-img.oss-cn-beijing.aliyuncs.com/xdclass_pro/default/head_img/14.jpeg",
            "https://xd-video-pc-img.oss-cn-beijing.aliyuncs.com/xdclass_pro/default/head_img/15.jpeg"
    };

    /**
     * Method               register
     * Description          用户注册接口
     *                      <将用户密码用MD5加密,随机生成头像,设置创建时间>
     * Author               Lin
     * Date                 2021/7/19 21:15
     * Version              1.0.0
     * @param               registerUserInfo    用户注册信息
     * @return int         返回插入数量,插入成功返回插入数量，否则返回-1表示插入失败
     */
    @Override
    @Transactional
    public int register(User registerUserInfo) {

        if (registerUserInfo != null) {
            registerUserInfo.setPwd(CommonUtils.MD5Encrypt(registerUserInfo.getPwd()));
            registerUserInfo.setHeadImg(getRandomImg());
            registerUserInfo.setCreateTime(LocalDateTime.now());
            try {
                naturalKey = registerUserInfo.getLogin();
                operator = registerUserInfo.getName();
                request = JSONUtil.objectToJson(registerUserInfo);
                logInfo = registerUserInfo.toString();
                LocalDateTime startLocalDateTime = LocalDateTime.now();
                count = userMapper.register(registerUserInfo);


                LocalDateTime endLocalDateTime = LocalDateTime.now();
                response = "";
                runtime = DateTool.TimeCalculation(startLocalDateTime, endLocalDateTime, "NANOS");
                //插入正常日志
                logTemplate = LogTemplate.SetLogTemplate(
                        naturalKey,                     //业务主键
                        OperationType.INSERT,        //操作类型
                        Module.INPUT,                //日志模块
                        operator,                       //操作人
                        LogDirectory.INPUT,             //日志目录
                        Status.SUCCESS,                 //日志状态
                        count,                          //数量
                        request,                        //请求入参
                        response,                       //返回信息
                        logInfo,                        //日志正常信息
                        runtime                         //运行时间
                );
                LOGGER.info(logTemplate.toString());
                return count;
            } catch (Exception e) {
                count = 0;
                logError = e.toString();
                runtime = "[插入失败 无运行时间]";
                response = "返回失败信息";
                //插入错误日志
                logTemplate = LogTemplate.SetLogTemplate(
                        naturalKey,                     //业务主键
                        OperationType.INSERT,        //操作类型
                        Module.INPUT,                //日志模块
                        operator,                       //操作人
                        LogDirectory.INPUT,             //日志目录
                        Status.ERROR,                 //日志状态
                        count,                          //数量
                        request,                        //请求入参
                        response,                       //返回信息
                        logInfo,                        //日志正常信息
                        runtime                         //运行时间
                );
                LOGGER.error(logTemplate.toString());
                return -1;
            } finally {

                logService.insertLogInfo(logTemplate);
            }


        }

        return -1;
    }

    /**
     * Method               getRandomImg
     * Description          随机生成头像
     * Author               Lin
     * Date                 2021/7/19 21:51
     * Version              1.0.0
     * @param
     * @return java.lang.String    返回一个图片地址
     */
    private String getRandomImg() {


        int size = headImg.length;
        Random random = new Random();
        int index = random.nextInt(size);
        return headImg[index];
    }

    /*
     * Method               parseToUser
     * Description          将Map集合的userInfo,转换为User对象，并将用户密码MD5加密处理
     * Author               Lin
     * Date                 2021/7/19 21:37
     * Version              1.0.0
     * @param               userInfo    用户注册信息
     * @return com.ml.timi.model.entity.User    返回一个User对象

    public User parseToUser(Map<String, String> userInfo) {
        user.setLogin(userInfo.get("login"));
        user.setName(userInfo.get("name"));
        user.setCreateTime(LocalDateTime.now());
        user.setHeadImg(getRandomImg());
        user.setPhone(userInfo.get("phone"));
        String pwd = userInfo.get("pwd");
        //将用户密码MD5加密
        String pwdMD5 = CommonUtils.MD5(pwd);
        user.setPwd(pwdMD5);

        return user;
    }*/


    @Override
    public User findByPhone(String phone) {
        return null;
    }

    /**
     * @Method checkLogin
     * <p>111<p>     用户登录验证
     *                  <>
     * @Author Lin
     * @Date 2021/7/8 15:36
     * @Version 1.0.0
     * @param           login   登录账号
     * @param           pwd     登录密码
     * @return java.lang.String
     */
    @Override
    public String checkLogin(String login, String pwd) {

        user = userMapper.checkLogin(login, CommonUtils.MD5Encrypt(pwd));
        if (user == null) {
            return null;
        } else {
            return JWTUtils.geneJsonWebToken(user);
        }
    }

    @Override
    public User findUserByToken(Integer userId) {

        User user = userMapper.findByUserToken(userId);

        return user;
    }


}
