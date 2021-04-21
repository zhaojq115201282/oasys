package com.jikui.oasys.service.Impl;

import com.jikui.oasys.entity.Icon;
import com.jikui.oasys.mapper.IconMapper;
import com.jikui.oasys.service.IconService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: zhaojq
 * @Description:
 * @Date:Create：in 2020/6/21 11:41
 * @Modified By：
 **/
@Service
public class IconServiceImpl implements IconService {
    @Autowired
    private IconMapper iconMapper;

    @Override
    public List<Icon> selectAll() {
        return iconMapper.selectAll();
    }
}
