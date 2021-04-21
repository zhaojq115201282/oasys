package com.jikui.oasys.util;

import org.springframework.stereotype.Component;

/**
 * @Author: zhaojq
 * @Description:
 * @Date:Create：in 2020/6/20 16:23
 * @Modified By：
 **/
@Component
public class AjaxResult {
    private boolean success;
    private String message;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void ajaxFalse(String message){
        this.message = message;
        this.success = false;
    }

    public void ajaxTrue(String message){
        this.message = message;
        this.success = true;
    }
}
