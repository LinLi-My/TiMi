/**
 * ClassName:      WebserviceAuthinterceptor
 * Description:    Webservice 安全认证，拦截器
 * Date:           2021 2021/12/10 10:44
 * Author:         Lin
 * Copyright:      Lin
 */


package com.ml.timi.interceptor;

import org.apache.cxf.binding.soap.SoapMessage;
import org.apache.cxf.binding.soap.saaj.SAAJInInterceptor;
import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.phase.AbstractPhaseInterceptor;
import org.apache.cxf.phase.Phase;
import org.springframework.stereotype.Component;
import org.w3c.dom.NodeList;

import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPHeader;
import javax.xml.soap.SOAPMessage;

@Component
//@slf4j
public class WebserviceAuthInterceptor extends AbstractPhaseInterceptor<SoapMessage> {
    /** 用户名 */
    private static final String USER_NAME = "Lin";
    /** 密码 */
    private static final String USER_PASSWORD = "Lin1";
    /** 创建拦截器 */
    private SAAJInInterceptor saajInInterceptor = new SAAJInInterceptor();

    public WebserviceAuthInterceptor() {
        super(Phase.POST_PROTOCOL);
        /** 添加拦截器 */
        super.getAfter().add(SAAJInInterceptor.class.getName());

    }

    @Override
    public void handleMessage(SoapMessage message) throws Fault {

        System.out.println("==================SoapMessage =" + message);
        /** 获取指定消息 */
        SOAPMessage soapMessage = message.getContent(SOAPMessage.class);
        /** 判断有没有消息内容 */
        if (soapMessage == null) {
            /** 直接走默认处理 */
            this.saajInInterceptor.handleMessage(message);
            /** 尝试获取消息 */
            soapMessage = message.getContent(SOAPMessage.class);
        }
        /** SOAP的头信息 */
        SOAPHeader soapHeader = null;
        try {
            /** 通过消息获取头信息 */
            soapHeader = soapMessage.getSOAPHeader();
        } catch (SOAPException e) {
            e.printStackTrace();
        }
        /** 没有头信息 */
        if (soapHeader == null) {
            throw new Fault(new IllegalAccessException("找不到Header消息，无法实现用户认证处理"));
        }

        /** SOAP是基于XML文件结果进行传输的，所以如果要想获取认证信息，就必须进行相关的结构约定 */
        NodeList usernameNodeList = soapHeader.getElementsByTagName("username");    //获取指定的标签集合
        NodeList userpasswordNodeList = soapHeader.getElementsByTagName("userpassword");

        if (usernameNodeList.getLength() < 1) {
            throw new Fault(new IllegalAccessException("用户名错误"));
        }
        if (userpasswordNodeList.getLength() < 1) {
            throw new Fault(new IllegalAccessException("密码错误"));
        }

        String username = usernameNodeList.item(0).getTextContent().trim();
        String userpassword = userpasswordNodeList.item(0).getTextContent().trim();

        System.out.println("输出用户名" + username);
        System.out.println("输出密码" + userpassword);

        if (USER_NAME.equals(username) && USER_PASSWORD.equals(userpassword)) {
            System.out.println("用户认证成功");
        } else {
            SOAPException soapException = new SOAPException("用户认证失败"); //抛出异常
            System.out.println("用户认证失败");
            throw new Fault(soapException);

        }


    }
}
