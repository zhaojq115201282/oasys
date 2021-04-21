package com.jikui.oasys.service;

import com.jikui.oasys.entity.Role;
import com.jikui.oasys.util.PageBean;

import java.util.List;
import java.util.Map;

/**
 * @Author: zhaojq
 * @Description:
 * @Date:Create：in 2020/6/21 11:32
 * @Modified By：
 **/
public interface RoleService {
    List<Role> selectAll();

    PageBean<Role> queryPage(Map<String, Object> paramMap);

    int delByRoleIds(List<Integer> ids);

    Role selectById(Integer id);

    Role selectByName(String name);

    int editByRole(Role role);

    int insertRole(Role role);
}
