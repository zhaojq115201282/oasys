package com.jikui.oasys.mapper;

import com.jikui.oasys.entity.Log;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * @Author: zhaojq
 * @Description:
 * @Date:Create：in 2020/6/21 11:22
 * @Modified By：
 **/
@Mapper
public interface LogMapper {
    void insertByLog(Log log);

    /**
     * 根据条件查询登录日志
     * @param paramMap
     * @return
     */
    List<Log> queryList(Map<String, Object> paramMap);

    Integer queryCount(Map<String, Object> paramMap);

    int delByLogIds(List<Integer> ids);
}
