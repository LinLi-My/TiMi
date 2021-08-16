/**
 * ClassName:      LogDefiner
 * Description:    日志文件自定义
 * Date:           2021 2021/7/8 16:34
 * Author:         Lin
 * Copyright:      Lin
 */


package com.ml.timi.utils.other;

import ch.qos.logback.core.PropertyDefinerBase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.UUID;

public class LogDefiner extends PropertyDefinerBase {
    private static final Logger LOG = LoggerFactory.getLogger(LogDefiner.class);
    private String getUniqName() {
        String localIp = null;
        try {


            /*localIp = InetAddress.getLocalHost().getHostAddress();*/


        } catch (Exception e) {
            LOG.error("fail to get ip...", e);
        }
        String uniqName = UUID.randomUUID().toString().replace("-", "");
        if (localIp != null) {
            uniqName = localIp + "-" + uniqName;
        }
        return uniqName;
    }
    @Override
    public String getPropertyValue() {
        return getUniqName();
    }
}
