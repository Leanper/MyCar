package com.ygst.cenggeche.ui.activity.register;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.blankj.utilcode.utils.LogUtils;
import com.ygst.cenggeche.R;
import com.ygst.cenggeche.bean.CodeBean;
import com.ygst.cenggeche.mvp.MVPBaseActivity;
import com.ygst.cenggeche.ui.activity.registerinfo.RegisterInfoActivity;
import com.ygst.cenggeche.ui.widget.TimeCount;
import com.ygst.cenggeche.utils.CommonUtils;
import com.ygst.cenggeche.utils.ToastUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class RegisterActivity extends MVPBaseActivity<RegisterContract.View, RegisterPresenter> implements RegisterContract.View {

    private String TAG="RegisterActivity";
    private TimeCount timeCount;

    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.et_phone)
    EditText mEtPhone;
    @BindView(R.id.et_sms_code)
    EditText mEtSmsCode;
    @BindView(R.id.btn_get_sms_code)
    Button mBtnGetSmsCode;

    //获取验证
    @OnClick(R.id.btn_get_sms_code)
    public void getSmsCode() {
        //先校验账号是否被注册,成功后在获取验证码
        try {
            mPresenter.checkIsRegist(mEtPhone.getText().toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //下一步（校验验证码是否正确）
    @OnClick(R.id.btn_next)
    public void nextStep() {
        CommonUtils.startActivity(this,RegisterInfoActivity.class);
//        mPresenter.checkSMSCode(mEtPhone.getText().toString(), mEtSmsCode.getText().toString());
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_register;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        mTvTitle.setText("注册账号");
        timeCount = new TimeCount(60000, 1000);
        timeCount.setButton(mBtnGetSmsCode);
    }

    /**
     * 手机号未被注册，可以获取验证码
     */
    @Override
    public void checkIsRegistSuccess() {
        LogUtils.i(TAG,"账号未被注册，可以使用");
        try {
            timeCount.start();
            mPresenter.getSMSCode(mEtPhone.getText().toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void checkIsRegistError() {
        ToastUtil.show(this, "访问服务器失败");
    }

    @Override
    public void getSMSCodeSuccess(CodeBean codeBean) {
        ToastUtil.show(this, "获取验证码成功");
    }

    @Override
    public void getSMSCodeError() {
        ToastUtil.show(this, "获取验证码失败");
        timeCount.cancel();
        timeCount.onFinish();
    }

    @Override
    public void checkSMSCodeSuccess(CodeBean codeBean) {
        ToastUtil.show(this, "验证码校验成功");
        timeCount.cancel();
        timeCount.onFinish();

        Intent intent = new Intent();
        intent.putExtra("username",mEtPhone.getText().toString());
        intent.setClass(this,RegisterInfoActivity.class);
        startActivity(intent);
        CommonUtils.finishActivity(this);
    }

    @Override
    public void checkSMSCodeError() {
        ToastUtil.show(this, "验证码校验失败");
    }
}
