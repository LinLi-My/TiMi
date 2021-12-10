package com.ml.timi.config.webservices;


import com.ml.timi.interceptor.WebserviceAuthInterceptor;
import com.ml.timi.service.impl.AuthServiceImpl;
import com.ml.timi.service.impl.TestWebServiceImpl;
import org.apache.cxf.Bus;
import org.apache.cxf.bus.spring.SpringBus;
import org.apache.cxf.jaxws.EndpointImpl;
import org.apache.cxf.transport.servlet.CXFServlet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.xml.ws.Endpoint;

/**
 * ClassName:      WebServiceCallConfig
 * Description:    接口发布
 * Date:           2021 2021/7/20 9:04
 * Author:         Lin
 * Copyright:      Lin
 */

@Configuration
public class WebServiceConfig {

    @Autowired
    private WebserviceAuthInterceptor webserviceAuthInterceptor;

    @Bean
    public ServletRegistrationBean dispatcherServlets() {

        return new ServletRegistrationBean(new CXFServlet(), "/webservice/*");//发布服务名称
    }

    @Bean(name = Bus.DEFAULT_BUS_ID)
    public SpringBus springBus() {
        return new SpringBus();
    }


    //
    @Bean
    public Endpoint TestServiceEndpoint() {
        EndpointImpl endpoint = new EndpointImpl(this.springBus(),new TestWebServiceImpl());
        endpoint.publish("/TestWebService");
        /** 访问拦截 */
        endpoint.getInInterceptors().add(this.webserviceAuthInterceptor);
        return endpoint;
    }


    @Bean
    public Endpoint AuthServiceEndpoint() {
        EndpointImpl endpoint = new EndpointImpl(this.springBus(),new AuthServiceImpl());
        endpoint.publish("/AuthService");
        /** 访问拦截 */
        endpoint.getInInterceptors().add(this.webserviceAuthInterceptor);
        return endpoint;
    }



}
