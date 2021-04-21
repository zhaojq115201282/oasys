package com.jikui.oasys.controller;

import com.jikui.oasys.entity.Admin;
import com.jikui.oasys.entity.Holiday;
import com.jikui.oasys.service.HolidayService;
import com.jikui.oasys.util.Const;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @Author: zhaojq
 * @Description:
 * @Date:Create：in 2020/6/21 12:14
 * @Modified By：
 **/
@Controller
@RequestMapping("/manager")
public class HolidayController {

    @Autowired
    private HolidayService holidayService;

    @ApiOperation(value = "查询假条")
    @GetMapping("/getHolidays")
    public Object getHoliday(HttpSession session) {
        Admin admin = (Admin) session.getAttribute(Const.ADMIN);
        List<Holiday> holidaysList = holidayService.queryHoliday(admin.getUsername());
        return holidaysList;
    }

    @ApiOperation(value = "插入假条")
    @GetMapping("/addHolidays")
    public void insertHoliday(Holiday holiday) {
        holidayService.insertHoliday(holiday);
    }
}
