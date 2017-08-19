package com.ygst.cenggeche.ui.activity.login;

import android.app.ProgressDialog;
import android.util.Log;

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
    //  获取验证码
    @Override
    public void getSMSCode(String phone) throws Exception {

        HttpManager.getHttpManager().getMethod(UrlUtils.GET_SMSCODE_MERCHANT + "?phone=" + phone, new Observer<String>() {
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

    // 检查验证码是否输入成功
    @Override
    public void checkSMSCode(String phone, String code, String registration_id) {

        final ProgressDialog progressDialog = CommonUtils.showProgressDialog(mView.getContext(), "正在登录");
        String sign = MD5Util.string2MD5(phone+code+UrlUtils.KEY);
        Map<String, String> map = new HashMap<>();
        map.put("phone", phone);
        map.put("smsCode",code);
        map.put("registration_id",registration_id);
        map.put("sign", sign);
//       +"?phone="+phone+"&smsCode="+code+"&sign="+sign
        HttpManager.getHttpManager().postMethod(UrlUtils.CHECK_LOGIN_MERCHANT, new Observer<String>() {

            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                progressDialog.dismiss();
                if (mView != null)
                    mView.checkSMSCodeError();
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
                        mView.checkSMSCodeSuccess(loginBean);
                } else {
                    ToastUtil.show(mView.getContext(), loginBean.getMsg());
                }
            }
        }, map);

    }
}
