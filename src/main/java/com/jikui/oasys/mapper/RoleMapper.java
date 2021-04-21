package com.jikui.oasys.mapper;

import com.jikui.oasys.entity.Role;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * @Author: zhaojq
 * @Description:
 * @Date:Create：in 2020/6/21 11:23
 * @Modified By：
 **/
@Mapper
public interface RoleMapper {
    List<Role> selectAll();

    List<Role> queryList(Map<String, Object> paramMap);

    Integer queryCount(Map<String, Object> paramMap);

    int delByRoleIds(List<Integer> ids);

    Role selectById(Integer id);

    Role selectByName(String name);

    int editByRole(Role role);

    int insertRole(Role role);

    @Select("select role.id,role.name from role,admin where admin.rid = role.id and admin.id = #{adminId}")
    List<Role> findByAdminId(Integer adminId);

    @Select("select role.id,role.name from role,role_treemenu,treemenu where role.id = role_treemenu.rid " +
            "and role_treemenu.tid = treemenu.id and treemenu.id = #{treeMenuId}")
    List<Role> findByTreeMenuId(Integer treeMenuId);
}
