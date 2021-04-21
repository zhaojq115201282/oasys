package com.jikui.oasys.interceptor;

import com.jikui.oasys.entity.Admin;
import com.jikui.oasys.entity.Log;
import com.jikui.oasys.service.LogService;
import com.jikui.oasys.util.Const;
import com.jikui.oasys.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @Author: zhaojq
 * @Description:日志拦截器
 * @Date:Create：in 2020/6/20 17:15
 * @Modified By：
 **/
public class LogInterceptor implements HandlerInterceptor {

    @Autowired
    private Log log;
    @Autowired
    private LogService logService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        String stringDate = DateUtil.getStringDate("yyyy-MM-dd HH:mm:ss");
        String username = null;
        HttpSession session=request.getSession(false);
        if(session==null){
            return;
        }
        if(request.getSession().getAttribute(Const.ADMIN) != null){
            Admin admin = (Admin)request.getSession().getAttribute(Const.ADMIN);
            username = admin.getUsername();
        }
        String uri = request.getRequestURI();
        if(uri.equals("/manager/logList")){
            return;
        }

        log.setLogTime(stringDate);
        log.setUsername(username);
        log.setUri(uri);

        logService.insertByLog(log);
    }

}
