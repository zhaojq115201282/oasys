package com.jikui.oasys.service.Impl;

import com.jikui.oasys.entity.Role;
import com.jikui.oasys.mapper.RoleMapper;
import com.jikui.oasys.service.RoleService;
import com.jikui.oasys.util.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * @Author: zhaojq
 * @Description:
 * @Date:Create：in 2020/6/21 11:43
 * @Modified By：
 **/
@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleMapper roleMapper;

    /**
     * 查询所有角色
     * @return
     */
    @Override
    public List<Role> selectAll() {
        return roleMapper.selectAll();
    }

    /**
     * 分页查询角色信息
     * @param paramMap
     * @return
     */
    @Override
    public PageBean<Role> queryPage(Map<String, Object> paramMap) {
        PageBean<Role> pageBean = new PageBean<>((Integer) paramMap.get("pageno"),(Integer) paramMap.get("pagesize"));

        Integer startIndex = pageBean.getStartIndex();
        paramMap.put("startIndex",startIndex);
        List<Role> datas = roleMapper.queryList(paramMap);
        pageBean.setDatas(datas);

        Integer totalsize = roleMapper.queryCount(paramMap);
        pageBean.setTotalsize(totalsize);
        return pageBean;
    }

    /**
     * 根据id删除角色信息
     * @param ids
     * @return
     */
    @Override
    @Transactional
    public int delByRoleIds(List<Integer> ids) {
        try {
            return roleMapper.delByRoleIds(ids);
        } catch (Exception e) {
            //存在管理员拥有此角色，无法删除
            return 0;
        }
    }

    /**
     * 根据id查询角色信息
     * @param id
     * @return
     */
    @Override
    public Role selectById(Integer id) {
        return roleMapper.selectById(id);
    }

    /**
     * 根据用户名查询角色信息
     * @param name
     * @return
     */
    @Override
    public Role selectByName(String name) {
        return roleMapper.selectByName(name);
    }

    /**
     * 更新角色
     * @param role
     * @return
     */
    @Override
    public int editByRole(Role role) {
        return roleMapper.editByRole(role);
    }

    /**
     * 增加角色
     * @param role
     * @return
     */
    @Override
    public int insertRole(Role role) {
        return roleMapper.insertRole(role);
    }
}
