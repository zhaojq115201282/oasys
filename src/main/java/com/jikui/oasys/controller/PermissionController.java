package com.jikui.oasys.controller;

import com.jikui.oasys.config.ReloadSecuritySource;
import com.jikui.oasys.entity.Icon;
import com.jikui.oasys.entity.TreeMenu;
import com.jikui.oasys.service.IconService;
import com.jikui.oasys.service.TreeMenuService;
import com.jikui.oasys.util.AjaxResult;
import com.jikui.oasys.util.Const;
import com.jikui.oasys.util.Data;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;

/**
 * @Author: zhaojq
 * @Description:
 * @Date:Create：in 2020/6/21 12:19
 * @Modified By：
 **/
@Controller
@RequestMapping("/manager")
public class PermissionController {

    @Autowired
    private AjaxResult ajaxResult;
    @Autowired
    private TreeMenuService treeMenuService;
    @Autowired
    private IconService iconService;

    @Autowired
    private ReloadSecuritySource reloadSecuritySource;

    /**
     * 跳转登录界面
     * @return
     */
    @ApiOperation(value = "跳转登录界面")
    @GetMapping("/permission")
    public String permission(){
        return "manager/permission/permissionList";
    }


    /**
     * 异步加载权限树
     * @param session
     * @return
     */
    @ApiOperation(value = "异步加载权限树")
    @GetMapping("/permissionList")
    @ResponseBody
    public Object permissionList(HttpSession session, Integer id){
        List<TreeMenu> treeMenuList = treeMenuService.selectAll();
        if(id != null){
            List<TreeMenu> atList = treeMenuService.selectByRoleId(id);
            for(TreeMenu info : treeMenuList){
                for(TreeMenu at : atList){
                    if(info.getId().equals(at.getId())){
                        info.setChecked(true);
                    }
                }
            }
        }
        HashMap<String, Object> rest = new HashMap<>();
        rest.put("code",0);
        rest.put("msg","ok");
        rest.put("data",treeMenuList);

        return rest;
    }

    /**
     * 跳转页面
     * @param type
     * @param id
     * @param model
     * @return
     */
    @ApiOperation(value = "跳转页面")
    @GetMapping("/addPermission")
    public String addPermission(String type, Integer id, Model model, HttpSession session){
        //加载icon列表,存入session
        if(session.getAttribute(Const.ICON) == null){
            List<Icon> iconList = iconService.selectAll();
            session.setAttribute(Const.ICON,iconList);
        }
        if(type != null && type.equals("edit")){
            TreeMenu treeMenu = treeMenuService.selectById(id);
            model.addAttribute(Const.TREEMENU,treeMenu);
        }else if(type != null && type.equals("add")){
            model.addAttribute("pid",id); //设为父id
        }else if(type != null && type.equals("addParent")){
            return "manager/permission/addParentNode";
        }
        return "manager/permission/addPermission";
    }

    /**
     * 添加修改权限
     * @param treeMenu
     * @return
     */
    @ApiOperation(value = "添加修改权限")
    @PostMapping("/addPermission")
    @ResponseBody
    public AjaxResult submitAddPermission(TreeMenu treeMenu){
        TreeMenu byName = treeMenuService.selectByName(treeMenu.getName());
        TreeMenu byUrl = treeMenuService.selectByUrl(treeMenu.getUrl());

        if(treeMenu.getId() !=null){
            //修改
            if(byName != null && !byName.getId().equals(treeMenu.getId())){
                ajaxResult.ajaxFalse("权限名已存在");
                return ajaxResult;
            }
            if(byUrl != null && !byUrl.getId().equals(treeMenu.getId())){
                ajaxResult.ajaxFalse("地址已存在");
                return ajaxResult;
            }
            int count = treeMenuService.editByPermission(treeMenu);
            if(count > 0){
                ajaxResult.ajaxTrue("修改成功");
            }else{
                ajaxResult.ajaxFalse("修改失败");
            }
        }else{
            //添加
            if(byName != null){
                ajaxResult.ajaxFalse("权限名已存在");
                return ajaxResult;
            }
            if(byUrl != null){
                ajaxResult.ajaxFalse("地址已存在");
                return ajaxResult;
            }
            //若是添加父节点
            if(treeMenu.getPid().equals(-1)){
                treeMenu.setUrl("-1");
            }
            int count = treeMenuService.insertPermission(treeMenu);
            if(count > 0){
                ajaxResult.ajaxTrue("添加成功");
            }else{
                ajaxResult.ajaxFalse("添加失败");
            }
        }
        reloadSecuritySource.getReloadSecuritySource();
        return ajaxResult;
    }

    /**
     * 删除权限
     * @param data
     * @return
     */
    @ApiOperation(value = "删除权限")
    @PostMapping("/delPermission")
    @ResponseBody
    public AjaxResult delPermission(Data data){
        List<TreeMenu> treeMenuList = treeMenuService.selectByPid(data.getIds().get(0));
        if(treeMenuList.size() !=0 ){
            ajaxResult.ajaxFalse("请先删除子节点");
            return ajaxResult;
        }
        int count = treeMenuService.delByPermissionIds(data.getIds());
        if(count >= data.getIds().size()){
            ajaxResult.ajaxTrue("删除成功");
        }else{
            ajaxResult.ajaxFalse("删除失败");
        }
        reloadSecuritySource.getReloadSecuritySource();
        return ajaxResult;
    }

    /**
     * 给角色分配权限
     * @param data
     * @param id
     * @return
     */
    @ApiOperation(value = "给角色分配权限")
    @PostMapping("/allotPer")
    @ResponseBody
    public AjaxResult allotPre(Data data,Integer id){
        int count = treeMenuService.updateRolePermission(data.getIds(),id);
        if(count >= data.getIds().size()){
            ajaxResult.ajaxTrue("分配成功");
        }
        reloadSecuritySource.getReloadSecuritySource();
        return ajaxResult;
    }

}
