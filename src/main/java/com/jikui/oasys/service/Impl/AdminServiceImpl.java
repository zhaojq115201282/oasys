package com.jikui.oasys.service.Impl;

import com.jikui.oasys.entity.Admin;
import com.jikui.oasys.mapper.AdminMapper;
import com.jikui.oasys.service.AdminService;
import com.jikui.oasys.util.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @Author: zhaojq
 * @Description:
 * @Date:Create：in 2020/6/21 11:34
 * @Modified By：
 **/
@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminMapper adminMapper;

    /**
     * 用户登录 根据用户名密码查询用户对象
     *
     * @param username
     * @param password
     * @return
     */
    @Override
    public Admin login(String username, String password) {
        return adminMapper.login(username, password);
    }

    /**
     * 分页查询用户信息
     *
     * @param paramMap
     * @return
     */
    @Override
    public PageBean<Admin> queryPage(Map<String, Object> paramMap) {
        PageBean<Admin> pageBean = new PageBean<>((Integer) paramMap.get("pageno"), (Integer) paramMap.get("pagesize"));

        Integer startIndex = pageBean.getStartIndex();
        paramMap.put("startIndex", startIndex);
        List<Admin> datas = adminMapper.queryList(paramMap);
        pageBean.setDatas(datas);

        Integer totalsize = adminMapper.queryCount(paramMap);
        pageBean.setTotalsize(totalsize);
        return pageBean;
    }

    /**
     * 增加用户
     *
     * @param admin
     * @return
     */
    @Override
    public int insertAdmin(Admin admin) {
        return adminMapper.insertAdmin(admin);
    }

    /**
     * 根据id查询用户
     *
     * @param id
     * @return
     */
    @Override
    public Admin selectById(Integer id) {
        return adminMapper.selectById(id);
    }

    /**
     * 根据id删除用户
     *
     * @param ids
     * @return
     */
    @Override
    public int delByAdminIds(List<Integer> ids) {
        return adminMapper.delByAdminIds(ids);
    }

    /**
     * 更新用户信息
     *
     * @param admin
     * @return
     */
    @Override
    public int editByAdmin(Admin admin) {
        return adminMapper.editByAdmin(admin);
    }

    /**
     * 根据姓名查找用户
     *
     * @param username
     * @return
     */
    @Override
    public Admin selectByName(String username) {
        return adminMapper.selectByName(username);
    }

    /**
     * 根据Email查询用户
     *
     * @param email
     * @return
     */
    @Override
    public Admin selectByEmail(String email) {
        return adminMapper.selectByEmail(email);
    }

    /**
     * @param
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return adminMapper.findByName(s);
    }
}
