package com.jikui.oasys.mapper;

import com.jikui.oasys.entity.Icon;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Author: zhaojq
 * @Description:
 * @Date:Create：in 2020/6/21 11:21
 * @Modified By：
 **/
@Mapper
public interface IconMapper {
    List<Icon> selectAll();
}
