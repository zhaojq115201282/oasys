package com.jikui.oasys.entity;

import org.springframework.stereotype.Component;

/**
 * @Author: zhaojq
 * @Description:登录日志
 * @Date:Create：in 2020/6/21 10:29
 * @Modified By：
 **/
@Component
public class Log {
    private Integer id;
    private String logTime;
    private String username;
    private String uri;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLogTime() {
        return logTime;
    }

    public void setLogTime(String logTime) {
        this.logTime = logTime;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }
}
