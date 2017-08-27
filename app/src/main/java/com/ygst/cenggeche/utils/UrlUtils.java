package com.ygst.cenggeche.utils;

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



    //  退出登录（商家）
    public static String EXIT = "/login/storeLogout.do";
    // 刷新token
    public static String REFRESHTOKEN = "login/refreshToken.do";
    // 上传头像
    public static String UPLOADICON = "upload/uploadUserPic.do";
    // 上传身份证
    public static String UPLOADIDCARD = "upload/uploadUserIdcardPic.do";
    // 上传驾驶证主页
    public static String UPLOADCARID = "upload/uploadLicensePic.do";
    // 上传驾驶证副页
    public static String UPLOADCARDTWO = "upload/uploadLicenseAddPic.do";
    //  身份验证状态
    public static String SHENFENSTATE = "user/userCheck.do";
    // 驾驶车型
    public static String CARTYPE = "user/loadLicenseType.do";
    // 高关注度
    public static String HOTEYE = "car/loadCarListByAttention.do";
    // 高评价
    public static String HOTPIN = "car/loadCarListByPraise.do";
    // 搜索热门车品牌
    public static String HOTPINPAI = "car/loadPopularBrand.do";
    // 租客获取筛选页面车品牌
    public static String COSTOMTYPE = "car/loadCarInfoByCostom.do";
    // 租客获取筛选页面车系
    public static String COSTOMCX = "car/loadCarTypeByBrand.do";
    //  筛选页面
    public static String SXURL = "car/indexSearch.do";
    // 已完成订单
    public static String OVERORDER = "order/loadCompleteOrder.do";
    // 正在进行中
    public static String INGORDER = "order/loadCurrentOrder.do";
    // 已取消的订单
    public static String CLEARORDER = "order/loadCancelOrder.do";
    //  车辆信息
    public static String CARINFO = "car/getCarInfoById.do";
    // 更多车辆信息
    public static String CARMOREINFO = "car/getCarMoreInfoById.do";
    // 车辆收藏与取消收藏
    public static String COLLECT = "collect/collectionCar.do";
    // 获取当前订单状态
    public static String ORDERSTSTUS = "order/loadOrderStatus.do";
    // 订单确认费用
    public static String ORDERSURE = "order/confirmCost.do";
    // 首页轮播图
    public static String RHOMELUNBO = "car/loadIndexImage.do";
    //  订单完成提交用户评论
    public static String ORDEROVER = "order/orderComplete.do";
    // 删除订单
    public static String DELETEORDER = "order/logicDelOrder.do";
    // 收藏车辆列表
    public static String COLLECTLIST = "collect/collectionList.do";
    // 清空收藏
    public static String CLEARCOLLECT = "collect/emptied.do";
    // 取消订单
    public static String QXORDER = "order/cancelOrder.do";
    // 车主上传行驶证正面照片
    public static String UPRUNPIC = "upload/uploadRunLicense.do";
    // 车主上传行驶证正副页面
    public static String UPRUNTWO = "upload/uploadSidedRunLicense.do";
    // 上传登记证
    public static String UPDJZ = "upload/uploadRegister.do";
    // 获取登记证信息接口
    public static String CARRUNINFO = "carCheck/getDrivingInfo.do";
    // 上传保险接口
    public static String UPBAOXIAN = "upload/uploadInsurance.do";
    // 保存车辆基本信息
    public static String SAVEBASEINFO = "car/saveCarInfo.do";
    // 保存车辆更多信息接口
    public static String SAVEMORECAR = "car/saveCarMoreInfo.do";
    // 保存车辆上架信息
    public static String SAVESJINFO = "car/saveCarSellInfo.do";
    // 申请试驾接口
    public static String SQSJ = "car/applyTestDrive.do";
    // 车主加载我的车辆列表
    public static String CARLIST = "car/loadMyCar.do";
    // 用户下单试驾接口
    public static String RORDER = "order/saveOrder.do";
    // 车主获取订单列表
    public static String OWNERLIST = "order/getUserOrder.do";
    // 上传车辆图片信息
    public static String UPCARIMGS = "upload/uploadCarPic.do";
    // 车主端加载全部车辆品牌
    public static String CARALLPP = "car/loadAllBrand.do";
    // 车主加载全部车系
    public static String CARALLCX = "car/getCartypeByBrand.do";
    // 获取车主发布的基本信息
    public static String GETCARBI = "carCheck/getCarInfo.do";
    // 获取车主发布的更多车辆信息
    public static String GETMOREIF = "carCheck/getCarMoreInfo.do";
    //  车主获取车辆照片接口
    public static String GETCARIMG = "carCheck/getCarPic.do";
    // 车主改变车辆状态
    public static String CHANGESTATUS = "car/updateCarStatus.do";
    // 获取车主发布的上架信息接口
    public static String GETSJINFO = "carCheck/getCarSellInfo.do";
    // 获取用户个人信息
    public static String USERINFO = "user/getUserInfo.do";
    // 修改体验流程
    public static String UPTYSTATUS = "order/updateFlowStatus.do";
    // 车主删除订单
    public static String ODELETE = "order/userDelOrder.do";
    // 车主接受订单
    public static String ACCEPTORDER = "order/acceptOrder.do";
    // 车主拒绝订单
    public static String JJORDER = "order/refuseOrder.do";
    // 修改昵称
    public static String XGNIC = "user/updateNickName.do";
    // 检查认证状态
    public static String CHECKID = "user/checkCUserInfo.do";
    // 获取用户收益
    public static String HQSY = "user/userIncome.do";
    //  从服务器获取版本信息
    public static String GETCODE = "version/getNewAppVersion.do";
    // 是否开通申请试驾
    public static String KTSJ = "carCheck/openedTestDrive.do";
    // 联系车主
    public static String LXCZ = "sms/appSendCPhoneToUser.do";
    // 车主端获取车辆 状态
    public static String GETOCAR = "car/getCarStatus.do";


}
