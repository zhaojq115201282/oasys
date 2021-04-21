package com.jikui.oasys.mapper;

import com.jikui.oasys.entity.Holiday;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Author: zhaojq
 * @Description:
 * @Date:Create：in 2020/6/21 11:20
 * @Modified By：
 **/
@Mapper
public interface HolidayMapper {

    /**
     * 根据用户名查询假条
     * @param name
     * @return
     */
    List<Holiday> queryHoliday(String name);

    /**
     * 插入请假条
     * @param holiday
     */
    void insertHoliday(Holiday holiday);
}
