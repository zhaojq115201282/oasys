package com.jikui.oasys.controller;

import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author: zhaojq
 * @Description:
 * @Date:Create：in 2020/6/21 12:13
 * @Modified By：
 **/
@Controller
@RequestMapping("/manager/error")
public class ErrorController {
    @ApiOperation(value = "错误")
    @RequestMapping("/error403")
    public String error403(){
        return "/manager/error/error403";
    }
}
