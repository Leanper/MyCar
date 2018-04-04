package com.ygst.cenggeche.utils;

import static android.R.id.list;

/**
 * Created by Administrator on 2017/4/10.
 */

public class UrlUtils {
    //BaseUrl本地
     public static String BASEURl_BENDI = "http://192.168.0.133";
    // BaseUrl测试(暂时没有)
//     public static String BASEURl_TEST = "http://192.168.1.147:8080";
    //BaseUrl线上（暂时没有）
//    public static String BASEURL_XIANSHANG = "http://47.93.0.41:8080";

    //服务端地址
    public static String BASEURl = BASEURl_BENDI;

    //H5链接（本地）
//    public static String URL_H5="http://r16878y847.iok.la/car_mm";
    //H5链接（测试）
//    public static String URL_H5="http://192.168.1.148";
//    //H5链接（线上）
//    public static String URL_H5="http://m.1yongche.com";

    //MD5加密Key
    public static String KEY="A4G6GH71B28D205SFD7655H785ABE1F6";

    //校验用户帐号是否已被注册接口
    public static String CHECK_IS_REGIST = "/user/checkIsRegist.do";
    //获取验证码
    public static String GET_SMS_CODE = "/sms/getSMSCode.do ";
    //验证短信验证码是否正确或超时接口
    public static String CHECK_SMS_CODE = "/user/checkSmsCode.do";
    //注册接口
    public static String REGIST = "/user/regist.do";
    //登录接口
    public static String LOGIN = "/user/login.do";
    //重置密码接口
    public static String RESET_PASS = "/user/resetPass.do";

    // 刷新token
    public static String REFRESHTOKEN = "login/refreshToken.do";

    //获取全部行程（蹭车/捎人）
    public static String ALLTRAVEL = "/stroke/confirmStroke.do";

    //发布行程（车主/用户）接口
    public static String RELEASESTROKE = "/stroke/releaseStroke.do";

    //获取车辆品牌列表接口
    public static String GETALLCARBRAND = "/car/getAllCarBrand.do";

    //根据车辆品牌获取车系接口
    public static String GETALLCARTYPEBRAND = "/car/getCarTypeByBrand.do";
    //获取附近联系人
    public static String GETNEARBYPERSON = "/lookAround/list.do";



}
