/**
 * ClassName:
 * Description:
 * Date:           2021 2021/7/30 8:58
 * Author:         Lin
 * Copyright:      Lin
 */


package com.ml.timi.utils;

import lombok.Data;

@Data
public class ExpertionLin {

    public String messageL;
    public String classNameL;
    public String methodNameL;
    public String fileNameL;
    public int lineNumberL;

    @Override
    public String toString() {
        return  messageL +
                "\nat " + classNameL + "." + methodNameL + "(" + fileNameL + ":" + lineNumberL +")";
    }

    public static String Infor(Exception e) {
        ExpertionLin exceptionLin = new ExpertionLin();
        exceptionLin.setMessageL(e.toString());
        exceptionLin.setClassNameL(e.getStackTrace()[0].getClassName());
        exceptionLin.setMethodNameL(e.getStackTrace()[0].getMethodName());
        exceptionLin.setFileNameL(e.getStackTrace()[0].getFileName());
        exceptionLin.setLineNumberL(e.getStackTrace()[0].getLineNumber());
        return exceptionLin.toString();
    }
}
