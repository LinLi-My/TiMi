/**
 * ClassName:
 * Description:
 * Date:           2021 2021/7/29 13:50
 * Author:         Lin
 * Copyright:      Lin
 */


package com.ml.timi;


import com.ml.timi.utils.ExpertionLin;

public class Test {

    public static int A() {
        for (int i = 0; i < 10; i++) {

            try {
                if (i == 5) {
                    i = i / 0;
                }
                System.out.println(i);
            } catch (Exception e) {
                System.out.println(ExpertionLin.Infor(e));

                return 1;


            }


            System.out.println("----------");
        }
        return 2;
    }

    public static void main(String[] args) {

        System.out.println(Test.A());

    }
}
