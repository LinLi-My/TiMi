package com.ml.timi.interceptor;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ml.timi.config.enums.ResultEnum;
import com.ml.timi.utils.JWTUtils;
import com.ml.timi.utils.JsonData;
import io.jsonwebtoken.Claims;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

public class LoginInterceptor implements HandlerInterceptor {
    /**
     * 进入到 controller之前的方法
     *
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        try {
            String accesToken  = request.getHeader("token");
            if (accesToken  == null) {
                accesToken  = request.getParameter("token");
            }
            if (StringUtils.isNotBlank(accesToken )) {
                Claims claims = JWTUtils.checkJWT(accesToken );
                if (claims == null) {
                    //告诉登陆失败
                    sendJsonMessage(response, JsonData.resultData(ResultEnum.NOT_LOGIN));
                    return false;
                }
                Object id = claims.get("id");
                String name = (String) claims.get("name");

                request.setAttribute("user_id", id);
                request.setAttribute("name", name);
                return true;
            }

        } catch (Exception e) {
            e.printStackTrace();
            //登陆失败
            sendJsonMessage(response, JsonData.resultData(ResultEnum.NOT_LOGIN));
            return false;
        }
        sendJsonMessage(response, JsonData.resultData(ResultEnum.NOT_LOGIN));
        return false;
    }

    /**
     * 登陆不成功返回json数据
     * @param response
     * @param obj
     */

    public static void sendJsonMessage(HttpServletResponse response, Object obj) {

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            response.setContentType("application/json; charset=UTF-8");
            PrintWriter writer = response.getWriter();
            writer.print(objectMapper.writeValueAsString(obj));
            writer.close();
            response.flushBuffer();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
}
