package com.ygst.cenggeche.ui.activity.login;

import android.app.ProgressDialog;
import android.util.Log;

import com.blankj.utilcode.utils.LogUtils;
import com.google.gson.Gson;
import com.ygst.cenggeche.bean.CodeBean;
import com.ygst.cenggeche.bean.LoginBean;
import com.ygst.cenggeche.manager.GsonManger;
import com.ygst.cenggeche.manager.HttpManager;
import com.ygst.cenggeche.mvp.BasePresenterImpl;
import com.ygst.cenggeche.utils.CommonUtils;
import com.ygst.cenggeche.utils.MD5Util;
import com.ygst.cenggeche.utils.ToastUtil;
import com.ygst.cenggeche.utils.UrlUtils;

import java.util.HashMap;
import java.util.Map;

import rx.Observer;

/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class LoginPresenter extends BasePresenterImpl<LoginContract.View> implements LoginContract.Presenter {

    private static String TAG = "LoginPresenter";
    //校验用户是否注册
    @Override
    public void checkIsRegist(String username) {
        Map<String, String> map = new HashMap<>();
        map.put("username", username);
        HttpManager.getHttpManager().postMethod(UrlUtils.CHECK_IS_REGIST, new Observer<String>() {

            @Override
            public void onCompleted() {
            }

            @Override
            public void onError(Throwable e) {
                ToastUtil.show(mView.getContext(), "服务器出现问题，程序猿正在解决");
                LogUtils.e(TAG,"返回的onError",e);
            }

            @Override
            public void onNext(String s) {
                LogUtils.i("HttpManager", "ssss:" + s);
                Gson gson = new Gson();
                CodeBean codeBean = gson.fromJson(s, CodeBean.class);
                if ("0000".equals(codeBean.getCode())) {
                    if (mView != null) {
                        mView.unregistered();
                    }
                    ToastUtil.show(mView.getContext(), codeBean.getMsg());
                } else {
                    if (mView != null) {
                        mView.registered();
                    }
                    ToastUtil.show(mView.getContext(), codeBean.getMsg());
                }
            }
        }, map);
    }

    //  获取验证码
    @Override
    public void getSMSCode(String phone) throws Exception {

        HttpManager.getHttpManager().getMethod(UrlUtils.GET_SMS_CODE + "?phone=" + phone, new Observer<String>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                Log.i("LoginPresenter", "onError:++++++++++++ " + e.getMessage());
                if (mView != null)
                    mView.getSMSCodeError();

            }

            @Override
            public void onNext(String s) {
                Log.i("LoginPresenter", "onNext:++++++++++++------------------ " + s);

                CodeBean o = (CodeBean) GsonManger.getGsonManger().gsonFromat(s, new CodeBean());
                if ("0000".equals(o.getCode())) {
                    if (mView != null)
                        mView.getSMSCodeSuccess(o);
                } else {
                    ToastUtil.show(mView.getContext(), o.getMsg());
                }

            }
        });

    }

    // 验证码登录
    @Override
    public void login(LoginBean loginBean,String checkType) {

        final ProgressDialog progressDialog = CommonUtils.showProgressDialog(mView.getContext(), "正在登录");
        Map<String, String> map = new HashMap<>();
        map.put("username", loginBean.getData().getUsername());
        map.put("checkType",checkType);
        if(checkType.equals("1")){
            //密码登录
            String password = MD5Util.string2MD5(loginBean.getData().getPassword()+UrlUtils.KEY);
            map.put("password",password);
        }else{
            //验证码登录
            String smsCode = MD5Util.string2MD5(loginBean.getCode()+UrlUtils.KEY);
            map.put("smsCode",smsCode);
        }

        HttpManager.getHttpManager().postMethod(UrlUtils.LOGIN, new Observer<String>() {

            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                progressDialog.dismiss();
                if (mView != null)
                    mView.loginError();
                Log.i("checkSMSCodeError", "onError:+ ++++++++++++++" + e.toString());
            }

            @Override
            public void onNext(String s) {
                progressDialog.dismiss();
                Log.i("checkSMSCodeError", "onNext:+ ++++++++++++++" + s);
//                LoginBean loginBean = (LoginBean) GsonManger.getGsonManger().gsonFromat(s, new LoginBean());

                Gson gson = new Gson();
                LoginBean loginBean =gson.fromJson(s, LoginBean.class);

                if ("0000".equals(loginBean.getCode())) {
                    if (mView != null)
                        mView.loginSuccess(loginBean);
                } else {
                    ToastUtil.show(mView.getContext(), loginBean.getMsg());
                }
            }
        }, map);

    }
}
