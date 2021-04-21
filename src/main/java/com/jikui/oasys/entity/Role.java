package com.jikui.oasys.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;

/**
 * @Author: zhaojq
 * @Description:已授予的权限
 * @Date:Create：in 2020/6/21 10:30
 * @Modified By：
 **/
public class Role implements GrantedAuthority {
    private Integer id;
    private String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @JsonIgnore
    @Override
    //这个方法将返回此用户的所拥有的权限
    public String getAuthority() {
        return name;
    }
}
