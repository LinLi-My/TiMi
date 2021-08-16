/**
 * ClassName:
 * Description:
 * Date:           2021 2021/7/19 9:26
 * Author:         Lin
 * Copyright:      Lin
 */


package com.ml.timi.utils;

import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


public class SqlUtil {


    public <T> Object insertForEachList(List<T> list, Class tClass, Class parameterTypes, int groupSize, String name, SqlSessionFactory sqlSessionFactory) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException, InstantiationException {

      /*  Object object = tClass.newInstance();
        System.out.println(object.toString());*/

        Method method = tClass.getMethod(name, parameterTypes);
        //分组大小，每组500条数据
        //分组次数
        int groupCount = list.size() / groupSize;

        SqlSession sqlSession = sqlSessionFactory.openSession(ExecutorType.BATCH, false);
        Object t = sqlSession.getMapper(tClass);
        //System.out.println(t.toString());
        try {

            LocalDateTime aa = LocalDateTime.now();

            //优化批量插入
            Object aaa = "";
            if (list.size() <= groupSize) {

                method.invoke(sqlSession.getMapper(tClass), list);
            } else {
                List subList = new ArrayList();
                for (int i = 0; i < groupCount; i++) {
                    subList = list.subList(0, groupSize);
                    aaa = method.invoke(sqlSession.getMapper(tClass), subList);
                    list.subList(0, groupSize).clear();
                }
                if (list.size() > 0) {
                    method.invoke(sqlSession.getMapper(tClass), list);
                }
            }
            System.out.println("--------" + aaa);
            // int qwe = 1/0;
            sqlSession.commit();

            LocalDateTime bb = LocalDateTime.now();
            System.out.println(DateTool.TimeCalculation(aa, bb, "NANOS"));


        } catch (Exception e) {
            sqlSession.rollback();
            e.printStackTrace();
        } finally {
            sqlSession.close();
        }
        return 1;
    }
}
