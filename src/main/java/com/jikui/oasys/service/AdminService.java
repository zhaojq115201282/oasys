package com.jikui.oasys.service;

import com.jikui.oasys.entity.Admin;
import com.jikui.oasys.util.PageBean;
import org.springframework.security.core.userdetails.UserDetailsService;


import java.util.List;
import java.util.Map;

/**
 * @Author: zhaojq
 * @Description:用户服务接口
 * @Date:Create：in 2020/6/21 11:25
 * @Modified By：
 **/
public interface AdminService extends UserDetailsService {

    Admin login(String username, String password);

    PageBean<Admin> queryPage(Map<String, Object> paramMap);

    int insertAdmin(Admin admin);

    Admin selectById(Integer id);

    int delByAdminIds(List<Integer> ids);

    int editByAdmin(Admin admin);

    Admin selectByName(String username);

    Admin selectByEmail(String email);


}
