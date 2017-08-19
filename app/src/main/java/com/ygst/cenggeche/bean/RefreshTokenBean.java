package com.ygst.cenggeche.bean;

/**
 * Created by Administrator on 2017/4/24.
 */

public class RefreshTokenBean {
    private String code;
    private String accessToken;
    private String expiresIn;
    private String msg;
    private String tokenGenTime;

    public RefreshTokenBean(String code, String accessToken, String expiresIn, String msg, String tokenGenTime) {
        this.code = code;
        this.accessToken = accessToken;
        this.expiresIn = expiresIn;
        this.msg = msg;
        this.tokenGenTime = tokenGenTime;
    }

    public RefreshTokenBean() {
    }

    public String getCode() {
        return code;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public String getExpiresIn() {
        return expiresIn;
    }

    public String getMsg() {
        return msg;
    }

    public String getTokenGenTime() {
        return tokenGenTime;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public void setExpiresIn(String expiresIn) {
        this.expiresIn = expiresIn;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void setTokenGenTime(String tokenGenTime) {
        this.tokenGenTime = tokenGenTime;
    }
}
