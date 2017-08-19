package com.ygst.cenggeche.ui.activity.login;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.umeng.analytics.MobclickAgent;
import com.ygst.cenggeche.R;
import com.ygst.cenggeche.app.MyApplication;
import com.ygst.cenggeche.bean.CodeBean;
import com.ygst.cenggeche.bean.LoginBean;
import com.ygst.cenggeche.mvp.MVPBaseActivity;
import com.ygst.cenggeche.ui.activity.MainActivity;
import com.ygst.cenggeche.ui.widget.TimeCount;
import com.ygst.cenggeche.utils.CommonUtils;
import com.ygst.cenggeche.utils.TextViewUtils;
import com.ygst.cenggeche.utils.ToastUtil;
import com.ygst.cenggeche.webview.WebViewActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.jpush.im.android.api.JMessageClient;
import cn.jpush.im.api.BasicCallback;


/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class LoginActivity extends MVPBaseActivity<LoginContract.View, LoginPresenter> implements LoginContract.View, View.OnClickListener {

    private EditText et_code, et_phone;
    private Button bt_check, bt_getCode;
    private TimeCount timeCount;
    private TextWatcher textWatch = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            //s:变化前的所有字符； start:字符开始的位置； count:变化前的总字节数；after:变化后的字节数
            if (TextViewUtils.getText(et_code).length() >= 4) {
                //设置按钮可点击
                bt_check.setClickable(true);
                //设置按钮为正常状态
                bt_check.setPressed(true);
            } else {
                bt_check.setClickable(false);
                //设置按钮为按下状态
                bt_check.setPressed(false);
            }
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            //S：变化后的所有字符；start：字符起始的位置；before: 变化之前的总字节数；count:变化后的字节数
            if (TextViewUtils.getText(et_code).length() >= 4) {
                //设置按钮可点击
                bt_check.setClickable(true);
                //设置按钮为正常状态
                bt_check.setPressed(true);
            } else {
                bt_check.setClickable(false);
                //设置按钮为按下状态
                bt_check.setPressed(false);
            }

        }

        @Override
        public void afterTextChanged(Editable s) {
            //s:变化后的所有字符

        }
    };

    @BindView(R.id.toolbar)
    Toolbar tbToolbar;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        setToolBar(tbToolbar, "登录", true);
        //找控件
        initView();
    }

    private void initView() {
        timeCount = new TimeCount(60000, 1000);
        //验证码
        et_code = (EditText) findViewById(R.id.et_code);

        et_code.setInputType(EditorInfo.TYPE_CLASS_PHONE);
        //手机号码
        et_phone = (EditText) findViewById(R.id.et_phone);

        et_phone.setInputType(EditorInfo.TYPE_CLASS_PHONE);
        //验证验证码
        bt_check = (Button) findViewById(R.id.bt_login);
        //获取验证码
        bt_getCode = (Button) findViewById(R.id.bt_getCode);
        findViewById(R.id.tv_tiaokuan).setOnClickListener(this);
        bt_check.setOnClickListener(this);
        bt_getCode.setOnClickListener(this);
        bt_getCode.setClickable(false);
        bt_check.setClickable(false);
        timeCount.setButton(bt_getCode);
        et_code.addTextChangedListener(textWatch);
        et_phone.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!CommonUtils.isUserNumber(TextViewUtils.getText(et_phone))) {
                    //设置按钮不可点击
                    bt_getCode.setClickable(false);
                    //设置按钮为按下状态
                    bt_getCode.setPressed(false);
                } else {
                    //设置按钮可点击
                    bt_getCode.setClickable(true);
                    //设置按钮为正常状态
                    bt_getCode.setPressed(true);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    @Override
    public void getSMSCodeSuccess(CodeBean codeBean) {
        ToastUtil.show(this, "获取验证码成功");
    }

    @Override
    public void getSMSCodeError() {
        timeCount.cancel();
        timeCount.onFinish();
        ToastUtil.show(this, "获取验证码失败");
        //设置按钮可点击
        bt_getCode.setClickable(true);
        //设置按钮为正常状态
        bt_getCode.setPressed(true);
    }

    @Override
    public void checkSMSCodeSuccess(LoginBean loginBean) {
        ToastUtil.show(this, "登录成功");
        timeCount.cancel();
        timeCount.onFinish();
        MyApplication.clearLogin();
        // 储存登陆状态
        MyApplication.setIsLoginEd(true);
        // 保存 uid
        MyApplication.saveUserId(loginBean.getUser().getUid() + "");
        // 保存 商家sid
        MyApplication.saveId(loginBean.getUser().getId() + "");
        // 保存商家用户名
        MyApplication.saveStoreOwn(loginBean.getUser().getStoreOwn());
        // 保存商家店名
        MyApplication.saveStore(loginBean.getUser().getStore());
        // 保存商家地址
        MyApplication.saveStoreAddr(loginBean.getUser().getStoreAddr());
        // 保存商家联系电话
        MyApplication.savePhone(loginBean.getUser().getPhone());

        /**
         *开启友盟账号统计
         * 使用第三方账号（如新浪微博）登录时
         * MobclickAgent.onProfileSignIn("WB"，"userID");
         */
        MobclickAgent.onProfileSignIn(loginBean.getUser().getUid()+"");
        CommonUtils.startActivity(this, MainActivity.class);
        CommonUtils.finishActivity(this);
    }

    @Override
    public void checkSMSCodeError() {
        ToastUtil.show(this, "检查验证码失败");

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_login:
                if (!TextUtils.isEmpty(TextViewUtils.getText(et_phone)) && !TextUtils.isEmpty(TextViewUtils.getText(et_code))) {

                    JMessageClient.login(TextViewUtils.getText(et_phone), TextViewUtils.getText(et_code), new BasicCallback() {
                        @Override
                        public void gotResult(int responseCode, String LoginDesc) {
                            if (responseCode == 0) {
                                Toast.makeText(getApplicationContext(), "登录成功", Toast.LENGTH_SHORT).show();
                                Log.i("MainActivity", "JMessageClient.login" + ", responseCode = " + responseCode + " ; LoginDesc = " + LoginDesc);
                                Intent intent = new Intent();
                                intent.setClass(LoginActivity.this, MainActivity.class);
                                startActivity(intent);
                                finish();
                            } else {
                                Toast.makeText(getApplicationContext(), "登录失败", Toast.LENGTH_SHORT).show();
                                Log.i("MainActivity", "JMessageClient.login" + ", responseCode = " + responseCode + " ; LoginDesc = " + LoginDesc);
                            }
                        }
                    });
                } else {
                    CommonUtils.showInfoDialog(this, "手机号码或验证码为空");
                }

//                if (!TextUtils.isEmpty(TextViewUtils.getText(et_phone)) && !TextUtils.isEmpty(TextViewUtils.getText(et_code))) {
//
//                    mPresenter.checkSMSCode(TextViewUtils.getText(et_phone), TextViewUtils.getText(et_code), MyApplication.getRegistrationId());
//                } else {
//                    CommonUtils.showInfoDialog(this, "手机号码或验证码为空");
//                }
                break;
            case R.id.bt_getCode:

                if (!TextUtils.isEmpty(TextViewUtils.getText(et_phone))) {
                    if (CommonUtils.isUserNumber(TextViewUtils.getText(et_phone))) {
                        try {
                            mPresenter.getSMSCode(TextViewUtils.getText(et_phone));

                            timeCount.start();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    } else {
                        CommonUtils.showInfoDialog(this, "请输入正确的手机号码");
                        //ToastUtil.show(this, "请输入正确的手机号码");
                    }
                } else {
                    CommonUtils.showInfoDialog(this, "请输入您的手机号码");
                    // ToastUtil.show(this, "请输入您的手机号码");
                }
                break;
            // 点击跳转 webView页面
            case R.id.tv_tiaokuan:
//                CommonUtils.jumptActivity(this, WebViewActivity2.class, "title", "服务协议", "webview", "http://m.1yongche.com/page/serviceProtocol/protocol.html");
                WebViewActivity.loadUrl(this,"http://m.1yongche.com/page/serviceProtocol/protocol.html","服务协议");
                overridePendingTransition(R.anim.screen_zoom_in, R.anim.screen_zoom_out);
                break;
        }
    }

}
