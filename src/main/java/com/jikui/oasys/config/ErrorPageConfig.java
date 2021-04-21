package com.jikui.oasys.config;

import org.springframework.boot.web.server.ErrorPage;
import org.springframework.boot.web.server.ErrorPageRegistrar;
import org.springframework.boot.web.server.ErrorPageRegistry;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

/**
 * @Author: zhaojq
 * @Description:错误页面配置
 * @Date:Create：in 2020/6/20 17:04
 * @Modified By：
 **/
@Component
public class ErrorPageConfig implements ErrorPageRegistrar {
    @Override
    public void registerErrorPages(ErrorPageRegistry registry) {
        ErrorPage error400Page = new ErrorPage(HttpStatus.BAD_REQUEST,"/manager/error/error400" );
        ErrorPage error401Page = new ErrorPage(HttpStatus.UNAUTHORIZED,"/manager/error/error401");
        ErrorPage error403Page = new ErrorPage(HttpStatus.FORBIDDEN,"/manager/error/error403");
        ErrorPage error500Page = new ErrorPage(HttpStatus.INTERNAL_SERVER_ERROR,"/manager/error/error500");
        registry.addErrorPages(error400Page,error401Page,error403Page,error500Page);
    }

}
