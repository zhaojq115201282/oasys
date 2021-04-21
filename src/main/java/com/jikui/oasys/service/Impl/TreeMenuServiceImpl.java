package com.jikui.oasys.service.Impl;

import com.jikui.oasys.entity.TreeMenu;
import com.jikui.oasys.mapper.TreeMenuMapper;
import com.jikui.oasys.service.TreeMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: zhaojq
 * @Description:
 * @Date:Create：in 2020/6/21 11:45
 * @Modified By：
 **/
@Service
public class TreeMenuServiceImpl implements TreeMenuService {

    @Autowired
    private TreeMenuMapper treeMenuMapper;

    /**
     * 根据用户id查询匹配主菜单
     *
     * @param id
     * @return
     */
    @Override
    public List<TreeMenu> selectByAdminId(Integer id) {
        //获取用户所有的全部权限(父,子权限)菜单
        List<TreeMenu> treeMenuList = treeMenuMapper.selectByAdminId(id);
        //保存所有的父(主)菜单
        List<TreeMenu> root = new ArrayList<>();
        //遍历所有菜单集合,如果是主菜单的话直接放入root集合
        for (TreeMenu treeMenu : treeMenuList) {
            //pid为-1,则为父(主)菜单
            if (treeMenu.getPid() == -1) {
                root.add(treeMenu);
            }
        }
        //这个是遍历所有主菜单,分别获取所有主菜单的所有子菜单
        for (TreeMenu treeMenu : root) {
            //获取所有子菜单 递归
            List<TreeMenu> childrenList = getchildrenMeun(treeMenu.getId(), treeMenuList);
            //这个是实体类中的子菜单集合
            treeMenu.setChildren(childrenList);
        }
        return root;
    }

    /**
     * 查询所有的菜单项
     *
     * @return
     */
    @Override
    public List<TreeMenu> selectAll() {
        return treeMenuMapper.selectAll();
    }

    /**
     * 根据菜单id查询菜单
     *
     * @param id
     * @return
     */
    @Override
    public TreeMenu selectById(Integer id) {
        return treeMenuMapper.selectById(id);
    }

    /**
     * 根据菜单名字查询菜单
     *
     * @param name
     * @return
     */
    @Override
    public TreeMenu selectByName(String name) {
        return treeMenuMapper.selectByName(name);
    }

    /**
     * 根据url查询菜单
     *
     * @param url
     * @return
     */
    @Override
    public TreeMenu selectByUrl(String url) {
        return treeMenuMapper.selectByUrl(url);
    }

    /**
     * 根据菜单编辑用户权限
     *
     * @param treeMenu
     * @return
     */
    @Override
    public int editByPermission(TreeMenu treeMenu) {
        return treeMenuMapper.editByPermission(treeMenu);
    }

    /**
     * 增加菜单
     *
     * @param treeMenu
     * @return
     */
    @Override
    public int insertPermission(TreeMenu treeMenu) {
        return treeMenuMapper.insertPermission(treeMenu);
    }

    /**
     * 根据菜单id删除菜单
     *
     * @param ids
     * @return
     */
    @Override
    @Transactional
    public int delByPermissionIds(List<Integer> ids) {
        treeMenuMapper.delRolePermission(ids);
        return treeMenuMapper.delByPermissionIds(ids);
    }

    /**
     * 根据权限pid查询菜单
     *
     * @param id
     * @return
     */
    @Override
    public List<TreeMenu> selectByPid(Integer id) {
        return treeMenuMapper.selectByPid(id);
    }

    /**
     * 根据角色id查询菜单
     *
     * @param id
     * @return
     */
    @Override
    public List<TreeMenu> selectByRoleId(Integer id) {
        return treeMenuMapper.selectByRoleId(id);
    }

    /**
     * 跟新角色菜单权限
     *
     * @param ids
     * @param id
     * @return
     */
    @Override
    @Transactional
    public int updateRolePermission(List<Integer> ids, Integer id) {
        treeMenuMapper.delRolePermissionByRid(id);
        return treeMenuMapper.updateRolePermission(ids, id);
    }


    /**
     * 递归获取子菜单
     *
     * @param id
     * @param allMeun
     * @return
     */
    public List<TreeMenu> getchildrenMeun(int id, List<TreeMenu> allMeun) {
        //用于保存子菜单
        List<TreeMenu> childrenList = new ArrayList<>();
        for (TreeMenu info : allMeun) {
            //判断当前的菜单标识是否等于主菜单的id
            if (info.getPid() == id) {
                //如果是的话 就放入主菜单对象的子菜单集合
                childrenList.add(info);
            }
        }

        //这里就是递归了，遍历所有的子菜单
        for (TreeMenu info : childrenList) {
            info.setChildren(getchildrenMeun(info.getId(), allMeun));
        }

        //如果子菜单为空的话，那就说明某菜单下没有子菜单了，直接返回空,子菜单为空的话就不会继续
        //迭代了
        if (childrenList != null && childrenList.size() == 0) {
            return null;
        }
        return childrenList;
    }

}
