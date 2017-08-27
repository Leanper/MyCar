package com.ygst.cenggeche.bean;

/**
 * Created by Administrator on 2017/8/27.
 */

public class UserBean {
    private String code;
    private String msg;

    private String username;            //手机号
    private String password;            //密码 进行MD5加密后传递
    private String nickname;            //昵称
    private String birthday;            //生日  时间格式 yyyy-MM-dd
    private String gender;            //性别	0：女   1：男   2：未知
    private String registrationId;            //极光推送唯一标识

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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getRegistrationId() {
        return registrationId;
    }

    public void setRegistrationId(String registrationId) {
        this.registrationId = registrationId;
    }
}
