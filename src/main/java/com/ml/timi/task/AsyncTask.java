/**
 * ClassName:
 * Description:
 * Date:           2021 2021/7/21 0:10
 * Author:         Lin
 * Copyright:      Lin
 */


package com.ml.timi.task;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
@Async
public class AsyncTask {
    public void task1() throws InterruptedException {

        /*System.out.println("task1开始");
        InterfaceCallController interfaceCallController = new InterfaceCallController();
        interfaceCallController.callTestWebService();
        System.out.println("task1结束");*/
    }
    public void task2() throws InterruptedException {

        System.out.println("task2开始");
        System.out.println("task2结束");
    }


}
