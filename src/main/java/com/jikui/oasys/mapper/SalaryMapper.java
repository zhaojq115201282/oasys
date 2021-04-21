package com.jikui.oasys.mapper;

import com.jikui.oasys.entity.Salary;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Author: zhaojq
 * @Description:
 * @Date:Create：in 2020/6/21 11:24
 * @Modified By：
 **/
@Mapper
public interface SalaryMapper {

    /**
     * 查询工资
     * @param id
     * @return
     */
    Salary querySalary(String name);

    /**
     * 设置工资
     * @param salary
     */
    void insertSalary(Salary salary);
}
