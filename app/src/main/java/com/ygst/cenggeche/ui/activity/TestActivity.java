package com.ygst.cenggeche.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

import com.blankj.utilcode.utils.LogUtils;
import com.umeng.analytics.MobclickAgent;
import com.ygst.cenggeche.R;
import com.ygst.cenggeche.ui.activity.base.BaseActivity;
import com.ygst.cenggeche.ui.activity.login.LoginActivity;
import com.ygst.cenggeche.utils.CommonUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import cn.jpush.im.android.api.JMessageClient;
import cn.jpush.im.android.api.model.UserInfo;
import im.sdk.debug.RegisterAndLoginActivity;


public class TestActivity extends BaseActivity {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_test;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}
