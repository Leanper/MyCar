package com.ygst.cenggeche.bean;

/**
 * Created by Administrator on 2017/5/23.
 */

public class CodeMsg {


    /**
     * code : 0015
     * msg : access_token失效
     */

    private String code;
    private String msg;
    private String status;
    private String checkStatus;
    // 是否试驾
    private String testDriveStatus;

    public String getTestDriveStatus() {
        return testDriveStatus;
    }

    public String getCheckStatus() {
        return checkStatus;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
