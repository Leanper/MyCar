package com.ygst.cenggeche.bean;

/**
 * Created by Administrator on 2017/4/21.
 */

public class LoginBean {

    private String code;
    private String msg;
    /**
     * user : {"uid":131,"platform":"","loginTime":"","phone":"18500632163","storeOwn":"耿","store":"北京亦庄金桥4s店","extend1":"","extend3":"","extend2":"","pass":"","id":3,"storeAddr":"中国北京市北京市大兴区康定街118号","storeStatus":1,"time":"1499158007","storeLev":1,"money":1122,"lan":116.5465769,"lat":39.7775266,"deviceId":"","passStatus":0,"payPass":"123456","storeCode":10002}
     */

    private UserBean user;

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

    public UserBean getUser() {
        return user;
    }

    public void setUser(UserBean user) {
        this.user = user;
    }


    public static class UserBean {
        /**
         * uid : 131
         * platform :
         * loginTime :
         * phone : 18500632163
         * storeOwn : 耿
         * store : 北京亦庄金桥4s店
         * extend1 :
         * extend3 :
         * extend2 :
         * pass :
         * id : 3
         * storeAddr : 中国北京市北京市大兴区康定街118号
         * storeStatus : 1
         * time : 1499158007
         * storeLev : 1
         * money : 1122
         * lan : 116.5465769
         * lat : 39.7775266
         * deviceId :
         * passStatus : 0
         * payPass : 123456
         * storeCode : 10002
         */

        private int uid;
        private String platform;
        private String loginTime;
        private String phone;
        private String storeOwn;
        private String store;
        private String extend1;
        private String extend3;
        private String extend2;
        private String pass;
        private int id;
        private String storeAddr;
        private int storeStatus;
        private String time;
        private int storeLev;
        private int money;
        private double lan;
        private double lat;
        private String deviceId;
        private int passStatus;
        private String payPass;
        private int storeCode;

        public int getUid() {
            return uid;
        }

        public void setUid(int uid) {
            this.uid = uid;
        }

        public String getPlatform() {
            return platform;
        }

        public void setPlatform(String platform) {
            this.platform = platform;
        }

        public String getLoginTime() {
            return loginTime;
        }

        public void setLoginTime(String loginTime) {
            this.loginTime = loginTime;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getStoreOwn() {
            return storeOwn;
        }

        public void setStoreOwn(String storeOwn) {
            this.storeOwn = storeOwn;
        }

        public String getStore() {
            return store;
        }

        public void setStore(String store) {
            this.store = store;
        }

        public String getExtend1() {
            return extend1;
        }

        public void setExtend1(String extend1) {
            this.extend1 = extend1;
        }

        public String getExtend3() {
            return extend3;
        }

        public void setExtend3(String extend3) {
            this.extend3 = extend3;
        }

        public String getExtend2() {
            return extend2;
        }

        public void setExtend2(String extend2) {
            this.extend2 = extend2;
        }

        public String getPass() {
            return pass;
        }

        public void setPass(String pass) {
            this.pass = pass;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getStoreAddr() {
            return storeAddr;
        }

        public void setStoreAddr(String storeAddr) {
            this.storeAddr = storeAddr;
        }

        public int getStoreStatus() {
            return storeStatus;
        }

        public void setStoreStatus(int storeStatus) {
            this.storeStatus = storeStatus;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public int getStoreLev() {
            return storeLev;
        }

        public void setStoreLev(int storeLev) {
            this.storeLev = storeLev;
        }

        public int getMoney() {
            return money;
        }

        public void setMoney(int money) {
            this.money = money;
        }

        public double getLan() {
            return lan;
        }

        public void setLan(double lan) {
            this.lan = lan;
        }

        public double getLat() {
            return lat;
        }

        public void setLat(double lat) {
            this.lat = lat;
        }

        public String getDeviceId() {
            return deviceId;
        }

        public void setDeviceId(String deviceId) {
            this.deviceId = deviceId;
        }

        public int getPassStatus() {
            return passStatus;
        }

        public void setPassStatus(int passStatus) {
            this.passStatus = passStatus;
        }

        public String getPayPass() {
            return payPass;
        }

        public void setPayPass(String payPass) {
            this.payPass = payPass;
        }

        public int getStoreCode() {
            return storeCode;
        }

        public void setStoreCode(int storeCode) {
            this.storeCode = storeCode;
        }
    }
}
