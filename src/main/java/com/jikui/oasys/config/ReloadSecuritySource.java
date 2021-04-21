package com.jikui.oasys.config;

import com.jikui.oasys.entity.Role;
import com.jikui.oasys.entity.TreeMenu;
import com.jikui.oasys.mapper.RoleMapper;
import com.jikui.oasys.mapper.TreeMenuMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: zhaojq
 * @Description:重新加载spring security资源
 * @Date:Create：in 2020/6/20 18:13
 * @Modified By：
 **/
@Component
public class ReloadSecuritySource {
    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private TreeMenuMapper treeMenuMapper;

    public void getReloadSecuritySource(){
        Map<RequestMatcher, Collection<ConfigAttribute>> map = new HashMap<>();
        for(TreeMenu m : treeMenuMapper.selectAll()){
            if(!StringUtils.isEmpty(m.getUrl())){
                AntPathRequestMatcher matcher = new AntPathRequestMatcher(m.getUrl());
                ArrayList<ConfigAttribute> configs = new ArrayList<>();
                for(Role r : roleMapper.findByTreeMenuId(m.getId())){
                    org.springframework.security.access.SecurityConfig config = new SecurityConfig(r.getName());
                    configs.add(config);
                }
                map.put(matcher,configs);
            }
        }
        new MyFilterSecurityMetadataSource(map);
    }
}
