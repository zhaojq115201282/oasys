package com.jikui.oasys.service.Impl;

import com.jikui.oasys.entity.Holiday;
import com.jikui.oasys.mapper.HolidayMapper;
import com.jikui.oasys.service.HolidayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: zhaojq
 * @Description:
 * @Date:Create：in 2020/6/21 11:40
 * @Modified By：
 **/
@Service
public class HolidayServiceImpl implements HolidayService {
    @Autowired
    private HolidayMapper holidayMapper;

    @Override
    public List<Holiday> queryHoliday(String name) {
        return holidayMapper.queryHoliday(name);
    }

    @Override
    public void insertHoliday(Holiday holiday) {
        holidayMapper.insertHoliday(holiday);
    }
}
