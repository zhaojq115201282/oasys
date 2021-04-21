package com.jikui.oasys.service.Impl;

import com.jikui.oasys.entity.Salary;
import com.jikui.oasys.mapper.SalaryMapper;
import com.jikui.oasys.service.SalaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: zhaojq
 * @Description:
 * @Date:Create：in 2020/6/21 11:44
 * @Modified By：
 **/
@Service
public class SalaryServiceImpl implements SalaryService {
    @Autowired
    private SalaryMapper salaryMapper;

    @Override
    public Salary querySalary(String name) {
        return salaryMapper.querySalary(name);
    }

    @Override
    public void insertSalary(Salary salary) {
        salaryMapper.insertSalary(salary);
    }
}
