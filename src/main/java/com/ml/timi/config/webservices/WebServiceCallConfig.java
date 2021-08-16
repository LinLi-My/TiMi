/**
 * ClassName:      WebServiceCallConfig
 * Description:    接口调用配置
 * Date:           2021 2021/7/20 9:04
 * Author:         Lin
 * Copyright:      Lin
 */


package com.ml.timi.config.webservices;

import org.apache.cxf.endpoint.Client;
import org.apache.cxf.jaxws.endpoint.dynamic.JaxWsDynamicClientFactory;

public class WebServiceCallConfig {
    /**
     * TestWebService接口地址1
     */
    public static String TestWebService = "http://127.0.0.1:28888/webservice/TestWebService?wsdl";




    public static Client call(String url) {
        // 创建动态客户端
        JaxWsDynamicClientFactory dcf = JaxWsDynamicClientFactory.newInstance();
        //Client client = dcf.createClient(url);
        return dcf.createClient(url);
    }
}
