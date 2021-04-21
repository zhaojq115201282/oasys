package com.jikui.oasys.service.Impl;

import com.jikui.oasys.entity.Log;
import com.jikui.oasys.mapper.LogMapper;
import com.jikui.oasys.service.LogService;
import com.jikui.oasys.util.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @Author: zhaojq
 * @Description:登录接口实现
 * @Date:Create：in 2020/6/21 11:42
 * @Modified By：
 **/
@Service
public class LogServiceImpl implements LogService {
    @Autowired
    private LogMapper logMapper;

    /**
     * 增加登录日志
     * @param log
     */
    @Override
    public void insertByLog(Log log) {
        logMapper.insertByLog(log);
    }

    /**
     * 分页查询
     * @param paramMap
     * @return
     */
    @Override
    public PageBean<Log> queryPage(Map<String, Object> paramMap) {
        //根据传的参数 第几页 每页条数 构建分页对象
        PageBean<Log> pageBean = new PageBean<>((Integer) paramMap.get("pageno"),(Integer) paramMap.get("pagesize"));
        //开始索引
        Integer startIndex = pageBean.getStartIndex();
        paramMap.put("startIndex",startIndex);
        //分页查询出登录日志列表
        List<Log> datas = logMapper.queryList(paramMap);
        pageBean.setDatas(datas);
        //查询列表条数
        Integer totalsize = logMapper.queryCount(paramMap);
        pageBean.setTotalsize(totalsize);
        return pageBean;
    }

    /**
     * 根据日志id删除记录
     * @param ids
     * @return
     */
    @Override
    public int delByLogIds(List<Integer> ids) {
        return logMapper.delByLogIds(ids);
    }
}
