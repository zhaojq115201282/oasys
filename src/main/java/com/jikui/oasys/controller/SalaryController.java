package com.jikui.oasys.controller;

import com.jikui.oasys.entity.Admin;
import com.jikui.oasys.entity.Salary;
import com.jikui.oasys.service.SalaryService;
import com.jikui.oasys.util.Const;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

/**
 * @Author: zhaojq
 * @Description:个人工资
 * @Date:Create：in 2020/6/21 12:22
 * @Modified By：
 **/
@Controller
@RequestMapping("/manager")
public class SalaryController {

    @Autowired
    private SalaryService salaryService;

    @ApiOperation(value = "跳转个人工资页面", notes = "跳转个人工资页面")
    @GetMapping("/salary")
    public String salary() {
        return "manager/admin/adminSalary";
    }

    @PostMapping("getSalary")
    public Object getSalary( HttpSession session){
        Admin ad = (Admin) session.getAttribute(Const.ADMIN);
        Salary salary = salaryService.querySalary(ad.getUsername());

        return salary;
    }
}
