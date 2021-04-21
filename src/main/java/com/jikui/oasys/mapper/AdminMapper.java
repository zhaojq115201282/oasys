package com.jikui.oasys.mapper;

import com.jikui.oasys.entity.Admin;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

/**
 * @Author: zhaojq
 * @Description:
 * @Date:Create：in 2020/6/21 11:18
 * @Modified By：
 **/
@Mapper
public interface AdminMapper {

    Admin login(String username, String password);

    List<Admin> queryList(Map<String, Object> paramMap);

    Integer queryCount(Map<String, Object> paramMap);

    int insertAdmin(Admin admin);

    Admin selectById(Integer id);

    int delByAdminIds(List<Integer> ids);

    int editByAdmin(Admin admin);

    Admin selectByName(String username);

    Admin selectByEmail(String email);

    @Select("select * from admin where username = #{username}")
    @Results({
            @Result(id = true, property = "id",column = "id"),
            @Result(property = "roles", column = "id", javaType = List.class,
                    many = @Many(select = "com.jikui.oasys.mapper.RoleMapper.findByAdminId"))
    })
    Admin findByName(String username);
}
