package com.jikui.oasys.service;

import com.jikui.oasys.entity.Salary;

/**
 * @Author: zhaojq
 * @Description:
 * @Date:Create：in 2020/6/21 11:32
 * @Modified By：
 **/
public interface SalaryService {
    /**
     * 查询工资
     * @param name
     * @return
     */
    Salary querySalary(String name);

    /**
     * 设置工资
     * @param salary
     */
    void insertSalary(Salary salary);
}
