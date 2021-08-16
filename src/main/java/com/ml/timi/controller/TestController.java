package com.ml.timi.controller;

import com.ml.timi.config.WXConfig;
import com.ml.timi.config.template.LogDirectory;
import com.ml.timi.config.template.Module;
import com.ml.timi.config.template.OperationType;
import com.ml.timi.config.template.Status;
import com.ml.timi.config.enums.ResultEnum;
import com.ml.timi.mapper.TestMapper;
import com.ml.timi.model.entity.User;
import com.ml.timi.model.entity.VideoOrder;
import com.ml.timi.service.TestService;
import com.ml.timi.service.UserService;
import com.ml.timi.service.impl.TestServiceImpl;
import com.ml.timi.task.AsyncTask;
import com.ml.timi.utils.*;
import org.apache.ibatis.session.SqlSessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.InvocationTargetException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * ClassName:      TestController
 * Description:    测试类
 * Date:           2021 2021/7/8 14:36
 * Author:         Lin
 * Copyright:      Lin
 */
@RestController
@RequestMapping("api/v1/test")
public class TestController {

    @Resource
    private TestService testService;
    @Resource
    private TestServiceImpl testServiceImpl;
    @Resource
    private TestMapper testMapper;
    @Resource
    private UserService userService;
    @Resource
    private LogTemplate logTemplate;
    @Resource
    private User user;
    @Resource
    private VideoOrder videoOrder;
    @Resource
    private AsyncTask asyncTask;
    @Resource
    WXConfig wxConfig;

    @Resource
    SqlSessionFactory sqlSessionFactory;

    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());


    String naturalKey;
    String operator;
    String status;
    int count;
    String logInfo;
    String logError;


    @PostMapping("user")
    public Object findUserList(User user) {

        User user1 = testService.findUserById(user);
        System.out.println(user);
        return JsonData.resultData(ResultEnum.SELECT_SUCCESS, user1);

    }


    /**
     * @Description: 日志测试
     *                  <设置日志模板,保存日志并写入数据库>
     * @Author Lin
     * @Date 2021/7/8 14:08
     * @Param
     * @Return java.lang.Object
     * @Version 1.0.0
     */
    @RequestMapping("log")
    public Object logTest() throws Exception {


        try {
            Map<String, String> userInfo = new HashMap<>();
            userInfo.put("login", "login1");
            userInfo.put("name", "name");
            userInfo.put("pwd", "pwd");
            userInfo.put("head_img", "head_img");
            userInfo.put("phone", "phone");


            //设置日志
            naturalKey = userInfo.get("login");
            operator = userInfo.get("name");
            logInfo = userInfo.toString();
            //进行插入业务数据
            //count = userService.register(userInfo);

            logTemplate = LogTemplate.SetLogTemplate(

                    naturalKey,                     //业务主键
                    OperationType.UPDATE,        //操作类型
                    Module.INPUT,                //日志模块
                    operator,                       //操作人
                    LogDirectory.INPUT,             //日志目录
                    Status.SUCCESS,                 //日志状态
                    count,                          //数量
                    "r",
                    "reso",
                    logInfo,                        //日志正常信息
                    "运行时间");                      //日志异常信息
            LOGGER.info(logTemplate.toString());

        } catch (Exception e) {

            logTemplate = LogTemplate.SetLogTemplate(
                    naturalKey,                     //业务主键
                    OperationType.UPDATE,        //操作类型
                    Module.INPUT,                //日志模块
                    operator,                       //操作人
                    LogDirectory.OUTPUT,            //日志目录
                    Status.ERROR,                //日志状态
                    count,                          //数量
                    "r",
                    "reso",
                    logInfo,                        //日志正常信息
                    "运行时111间");                  //日志异常信息
            LOGGER.error(logTemplate.toString());
            throw new Exception("数据插入失败--");
        } finally {
            testService.insertLogInfo(logTemplate);
        }


        // int b = testService.insertLogInfoList(aa);


        return JsonData.resultData(ResultEnum.LOGIN_SUCCESS);
    }


    /**
     * Method               getIpAddress
     * Description          获取请求客户端的IP地址
     * Author               Lin
     * Date                 2021/7/19 16:25
     * Version              1.0.0
     * @param               request           请求客户端的入参
     * @return java.lang.String  返回IP地址
     */
    @RequestMapping("getIp")
    public String getIpAddress(HttpServletRequest request) {
        String ip = IpAddress.getIpAddress();

        return ip;
    }

    /**
     * Method               objectToJson
     * Description          将对象转换成JSON字符串
     * Author               Lin
     * Date                 2021/7/14 14:01
     * Version              1.0.0
     * @return java.lang.Object
     */
    @RequestMapping("objectToJson")
    public Object objectToJson() throws Exception {
        List<User> users = new ArrayList<User>();
        for (int i = 0; i < 2; i++) {
            List<VideoOrder> videoOrderLists = new ArrayList<VideoOrder>();
            User user = new User();
            user.setId(1008611 + i);
            user.setName("姓名" + i);
            user.setPhone("1234567789" + i);
            user.setPwd("用户密码" + i);
            user.setCreateTime(LocalDateTime.now());
            user.setHeadImg("用户图片" + i);
            for (int j = 0; j < 3; j++) {

                VideoOrder videoOrder = new VideoOrder();
                videoOrder.setOutTradeNo("明细" + j);
                videoOrder.setVideoImg("图片" + j);
                videoOrder.setVideoTitle("视频标题" + j);
                videoOrderLists.add(videoOrder);
            }

            user.setVideoOrderList(videoOrderLists);

            // 1、将实体类对象转换为json对象
            String objectToJson = JSONUtil.objectToJson(user);
            System.out.println("1、将实体类对象转换为json格式: " + objectToJson);
            users.add(user);
        }

        // 2、将List集合对象转换为json对象
        String jsonToPojo = JSONUtil.objectToJson(users);
        System.out.println("2、将List集合对象转换为json对象: " + jsonToPojo);

        // 3、将json数据转换成pojo对象list
        List<User> jsonToList = JSONUtil.jsonToList(jsonToPojo, User.class);
        System.out.println("3、将json数据转换成pojo对象list: " + jsonToList.toString());


        List<VideoOrder> videoOrderList1 = new ArrayList<>();
        for (User user1 : jsonToList) {

            // System.out.println("------"+ user1);

            System.out.println("------" + user1.getVideoOrderList());
            videoOrderList1.addAll(user1.getVideoOrderList());

        }
        System.out.println("----------000000------------" + videoOrderList1);


        List<String> stringList = new ArrayList<>();

        for (int i = 0; i < 20; i++) {
            String a = i + "-wo";
            stringList.add(a);
        }

        LocalDateTime a = LocalDateTime.now();
        // stringList.stream().skip(5).forEach(System.out::print);
        int index1 = stringList.size() / 5;
        for (int i = 0; i < index1; i++) {
            //stream流表达式，skip表示跳过前i*10000条记录，limit表示读取当前流的前10000条记录
            stringList.stream().skip(i * 10000).limit(10000).forEach(System.out::print);
        }

        LocalDateTime b = LocalDateTime.now();

        Duration duration = Duration.between(a, b);
        System.out.println("计算时间：" + duration);

        long days = duration.toDays(); //相差的天数
        long hours = duration.toHours();//相差的小时数
        long minutes = duration.toMinutes();//相差的分钟数
        long millis = duration.toMillis();//相差毫秒数
        long nanos = duration.toNanos();//相差的纳秒数


        LocalDateTime aa = LocalDateTime.now();
        /*for (String s : stringList) {
            System.out.println(s);
        }*/
        int batchCount = 10000;// 每批commit的个数
        int batchLastIndex = batchCount;// 每批最后一个的下标
        for (int index = 0; index < stringList.size(); ) {
            if (batchLastIndex >= stringList.size()) {
                batchLastIndex = stringList.size();
                System.out.println("index:" + index + " batchLastIndex:" + batchLastIndex);
                break;// 数据插入完毕，退出循环
            } else {
                System.out.println("index:" + index + " batchLastIndex:" + batchLastIndex);
                index = batchLastIndex;// 设置下一批下标
                batchLastIndex = index + (batchCount - 1);
            }
        }
        LocalDateTime bb = LocalDateTime.now();
        Duration duration1 = Duration.between(aa, bb);
        System.out.println("计算时间：" + duration1);

        long days1 = duration1.toDays(); //相差的天数
        long hours1 = duration1.toHours();//相差的小时数
        long minutes1 = duration1.toMinutes();//相差的分钟数
        long millis1 = duration1.toMillis();//相差毫秒数
        long nanos1 = duration1.toNanos();//相差的纳秒数
        System.out.println(a);
        System.out.println(b);
        System.out.println("发送短信耗时【 " + days + "天：" + hours + " 小时：" + minutes + " 分钟：" + millis + " 毫秒：" + nanos + " 纳秒】");

        System.out.println("------------------------------------------");
        System.out.println(aa);
        System.out.println(bb);
        System.out.println("发送短信耗时【 " + days1 + "天：" + hours1 + " 小时：" + minutes1 + " 分钟：" + millis1 + " 毫秒：" + nanos1 + " 纳秒】");

        return videoOrderList1;
    }

    // @Transactional
    @RequestMapping("TestinsertForeach")
    public Object TestinsertForeach() throws InvocationTargetException, NoSuchMethodException, IllegalAccessException, InstantiationException {

        List<LogTemplate> logTemplateList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {

            naturalKey = "业务主键测试1" + i;
            operator = "操作人1" + i;
            logInfo = "日志信息" + i;
            //进行插入业务数据
            /* count = userService.register(userInfo);*/
            LogTemplate logTemplate;
            logTemplate = LogTemplate.SetLogTemplate(
                    naturalKey,                     //业务主键
                    OperationType.UPDATE,        //操作类型
                    Module.INPUT,                //日志模块
                    operator,                       //操作人
                    LogDirectory.INPUT,             //日志目录
                    Status.SUCCESS,                 //日志状态
                    11,                          //数量
                    "r",
                    "reso",
                    logInfo,                        //日志正常信息
                    "运行时间"
            );
            LOGGER.info(logTemplate.toString());
            logTemplateList.add(logTemplate);
        }
        Class testServiceClass = TestMapper.class;
        Class parameterTypes = List.class;
        // SqlSession sqlSession = sqlSessionFactory.openSession(ExecutorType.BATCH,false);
        int groupSize = 5;
        String method = "TestinsertForeach";
        SqlUtil sqlUtil = new SqlUtil();
        sqlUtil.insertForEachList(logTemplateList, testServiceClass, parameterTypes, groupSize, method, sqlSessionFactory);


        //分组大小，每组500条数据
        //int groupSize = 1000;
        //分组次数
        /*int groupCount = logTemplateList.size() / groupSize;
        int a = 0;
        int b = 0;
        SqlSession sqlSession = sqlSessionFactory.openSession(ExecutorType.BATCH,false);
        TestMapper testMapper = sqlSession.getMapper(TestMapper.class);
        try {

            LocalDateTime aa = LocalDateTime.now();
            //优化批量插入
            if (logTemplateList.size() <= groupSize) {
                testMapper.TestinsertForeach(logTemplateList);
            } else {
                List<LogTemplate> subList = null;
                for (int i = 0; i < groupCount; i++) {
                    subList = logTemplateList.subList(0, groupSize);
                    a = a + testMapper.TestinsertForeach(subList);
                    logTemplateList.subList(0, groupSize).clear();
                }
                if (logTemplateList.size() > 0) {
                    a = a + testMapper.TestinsertForeach(logTemplateList);
                }
            }
            int cc = 1/0;
            //sqlSession.flushStatements();
            sqlSession.commit();

            LocalDateTime bb = LocalDateTime.now();
            System.out.println(DateTool.TimeCalculation(aa,bb,"YEARS"));


        } catch (Exception e) {
            sqlSession.rollback();
            e.printStackTrace();
        } finally {
            sqlSession.close();
        }*/


        return 1;
    }

    @RequestMapping("asyncTaskTest")
    public String asyncTaskTest() throws InterruptedException {


        LocalDateTime a = LocalDateTime.now();
        asyncTask.task1();
        asyncTask.task2();
        LocalDateTime b = LocalDateTime.now();
        System.out.println("一共用时计算：" + DateTool.TimeCalculation(a, b, DateTool.UNIT_HOURS));
        return DateTool.TimeCalculation(a, b, DateTool.UNIT_HOURS);
    }

    @RequestMapping("configTest")
    public String configTest(){


        String a = wxConfig.q;
        System.out.println(a);

        return a;
    }

}
