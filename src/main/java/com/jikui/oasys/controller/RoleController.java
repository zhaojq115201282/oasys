package com.jikui.oasys.controller;

import com.jikui.oasys.config.ReloadSecuritySource;
import com.jikui.oasys.entity.Role;
import com.jikui.oasys.service.RoleService;
import com.jikui.oasys.util.AjaxResult;
import com.jikui.oasys.util.Const;
import com.jikui.oasys.util.Data;
import com.jikui.oasys.util.PageBean;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: zhaojq
 * @Description:
 * @Date:Create：in 2020/6/21 12:21
 * @Modified By：
 **/
@Controller
@RequestMapping("/manager")
public class RoleController {

    @Autowired
    private AjaxResult ajaxResult;
    @Autowired
    private RoleService roleService;

    @Autowired
    private ReloadSecuritySource reloadSecuritySource;

    @GetMapping("/role")
    public String role(){
        return "manager/role/roleList";
    }

    /**
     * 异步加载角色列表
     * @param pageno
     * @param pagesize
     * @param rid
     * @return
     */
    @ApiOperation(value = "异步加载角色列表")
    @RequestMapping("/roleList")
    @ResponseBody
    public Object roleList(@RequestParam(value = "page", defaultValue = "1") Integer pageno,
                           @RequestParam(value = "limit", defaultValue = "5") Integer pagesize,
                           String rid){
        Map<String,Object> paramMap = new HashMap();
        paramMap.put("pageno",pageno);
        paramMap.put("pagesize",pagesize);

        //判断是否为空
        if(!StringUtils.isEmpty(rid) && !rid.equals("0")) paramMap.put("rid",rid);

        PageBean<Role> pageBean = roleService.queryPage(paramMap);

        Map<String,Object> rest = new HashMap();
        rest.put("code", 0);
        rest.put("msg", "");
        rest.put("count",pageBean.getTotalsize());
        rest.put("data", pageBean.getDatas());
        return rest;
    }


    /**
     * 删除角色
     * @param data
     * @return
     */
    @ApiOperation(value = "删除角色")
    @PostMapping("/delRole")
    @ResponseBody
    public AjaxResult delRole(Data data){
        int count = roleService.delByRoleIds(data.getIds());
        if(count >= data.getIds().size()){
            ajaxResult.ajaxTrue("删除成功");
            reloadSecuritySource.getReloadSecuritySource();
        }else{
            ajaxResult.ajaxFalse("存在管理员拥有此角色,无法删除");
        }
        return ajaxResult;
    }

    /**
     * 跳转添加角色页面
     * @param type
     * @param id
     * @param model
     * @return
     */
    @ApiOperation(value = "跳转添加角色页面")
    @GetMapping("/addRole")
    public String addRole(String type, Integer id, Model model){
        if(type != null && type.equals("edit")){
            Role role = roleService.selectById(id);
            model.addAttribute(Const.ROLE,role);
        }
        return "manager/role/addRole";
    }


    /**
     * 添加角色
     * @param role
     * @return
     */
    @ApiOperation(value = "添加角色")
    @PostMapping("/addRole")
    @ResponseBody
    public AjaxResult submitAddRole(Role role){
        Role byName = roleService.selectByName(role.getName());
        if(role.getId() !=null){
            //修改角色
            if(byName != null && !byName.getId().equals(role.getId())){
                //与修改角色一样，但存在数据库中，表示后来改的角色已存在
                ajaxResult.ajaxFalse("角色已存在");
                return ajaxResult;
            }
            int count = roleService.editByRole(role);
            if(count > 0){
                ajaxResult.ajaxTrue("修改成功");
            }else{
                ajaxResult.ajaxFalse("修改失败");
            }
        }else{
            //添加角色
            if(byName != null){
                //与原角色不一样，但存在数据库中，表示后来改的角色已存在
                ajaxResult.ajaxFalse("角色已存在");
                return ajaxResult;
            }
            int count = roleService.insertRole(role);
            if(count > 0){
                ajaxResult.ajaxTrue("添加成功");
            }else{
                ajaxResult.ajaxFalse("添加失败");
            }
        }
        reloadSecuritySource.getReloadSecuritySource();
        return ajaxResult;
    }

    @ApiOperation(value = "")
    @GetMapping("/allotPer")
    public String allotPer(Integer id,Model model){
        model.addAttribute("id",id);
        return "/manager/role/allotPer";
    }
}
