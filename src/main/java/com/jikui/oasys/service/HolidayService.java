package com.jikui.oasys.service;

import com.jikui.oasys.entity.Holiday;

import java.util.List;

/**
 * @Author: zhaojq
 * @Description:
 * @Date:Create：in 2020/6/21 11:28
 * @Modified By：
 **/
public interface HolidayService {
    List<Holiday> queryHoliday(String name);

    /**
     * 插入请假条
     * @param holiday
     */
    void insertHoliday(Holiday holiday);


}
