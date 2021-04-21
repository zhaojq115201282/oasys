package com.jikui.oasys.handler;


import com.alibaba.fastjson.JSON;
import com.jikui.oasys.util.AjaxResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author: zhaojq
 * @Description:
 * @Date:Create：in 2020/6/20 18:21
 * @Modified By：
 **/
@Component
public class LoginFailureHandler implements AuthenticationFailureHandler {
    @Autowired
    private AjaxResult ajaxResult;

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException e) throws IOException, ServletException {
        ajaxResult.ajaxFalse("登录失败");
        String json = JSON.toJSONString(ajaxResult);
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(json);
    }
}
