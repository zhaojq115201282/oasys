package com.jikui.oasys.service;

import com.jikui.oasys.entity.Log;
import com.jikui.oasys.util.PageBean;

import java.util.List;
import java.util.Map;

/**
 * @Author: zhaojq
 * @Description:
 * @Date:Create：in 2020/6/21 11:30
 * @Modified By：
 **/
public interface LogService {
    void insertByLog(Log log);

    PageBean<Log> queryPage(Map<String, Object> paramMap);

    int delByLogIds(List<Integer> ids);
}
