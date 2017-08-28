package com.ygst.cenggeche.ui.activity.registerinfo;

import android.app.ProgressDialog;

import com.blankj.utilcode.utils.LogUtils;
import com.google.gson.Gson;
import com.ygst.cenggeche.app.MyApplication;
import com.ygst.cenggeche.bean.CodeBean;
import com.ygst.cenggeche.bean.LoginBean;
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
 *  邮箱 784787081@qq.com
 */

public class RegisterInfoPresenter extends BasePresenterImpl<RegisterInfoContract.View> implements RegisterInfoContract.Presenter{

    private String TAG = "RegisterInfoPresenter";
    @Override
    public void registrationConfirm(LoginBean.DataBean userBean) {

        final ProgressDialog progressDialog = CommonUtils.showProgressDialog(mView.getContext(), "正在写入注册信息");
        String password = MD5Util.string2MD5(userBean.getPassword()+ UrlUtils.KEY);
        Map<String, String> map = new HashMap<>();
        map.put("username", userBean.getUsername());
        map.put("password",password);
        map.put("nickname",userBean.getNickname());
        map.put("birthday", userBean.getBirthday());
        map.put("gender", userBean.getGender()+"");
        map.put("registrationId", MyApplication.getRegistrationId());
        HttpManager.getHttpManager().postMethod(UrlUtils.REGIST, new Observer<String>() {

            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                progressDialog.dismiss();
                if (mView != null)
                    mView.registrationError();
                LogUtils.i(TAG, "onError:+ ++++++++++++++" + e.toString());
            }

            @Override
            public void onNext(String s) {
                progressDialog.dismiss();
                LogUtils.i(TAG, "onNext:+ ++++++++++++++" + s);
                Gson gson = new Gson();
                CodeBean codeBean =gson.fromJson(s, CodeBean.class);

                if ("0000".equals(codeBean.getCode())) {
                    if (mView != null)
                        mView.registrationSuccess();
                } else {
                    ToastUtil.show(mView.getContext(), codeBean.getMsg());
                }
            }
        }, map);
    }
}
