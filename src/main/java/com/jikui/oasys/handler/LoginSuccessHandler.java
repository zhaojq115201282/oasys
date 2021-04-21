package com.jikui.oasys.handler;

import com.jikui.oasys.config.ReloadSecuritySource;
import com.jikui.oasys.entity.Admin;
import com.jikui.oasys.entity.Role;
import com.jikui.oasys.service.RoleService;
import com.jikui.oasys.util.AjaxResult;
import com.jikui.oasys.util.Const;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import com.alibaba.fastjson.JSON;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

/**
 * @Author: zhaojq
 * @Description:
 * @Date:Create：in 2020/6/20 18:33
 * @Modified By：
 **/
@Component
public class LoginSuccessHandler implements AuthenticationSuccessHandler {

    @Autowired
    private AjaxResult ajaxResult;

    @Autowired
    private RoleService roleService;

    @Autowired
    private ReloadSecuritySource reloadSecuritySource;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        HttpSession session = request.getSession();
        Admin ad = (Admin) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        session.setAttribute(Const.ADMIN,ad);
        //获取角色列表，存入session
        if(session.getAttribute(Const.ROLE) == null){
            List<Role> roleList = roleService.selectAll();
            session.setAttribute(Const.ROLE,roleList);
        }

        // 装载权限
        reloadSecuritySource.getReloadSecuritySource();
        ajaxResult.ajaxTrue("登录成功");
        String json = JSON.toJSONString(ajaxResult);
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(json);
    }
}
