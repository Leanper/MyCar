package com.ygst.cenggeche.ui.activity.resetpwd;


import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.widget.EditText;
import android.widget.TextView;

import com.ygst.cenggeche.R;
import com.ygst.cenggeche.mvp.MVPBaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class ResetPwdActivity extends MVPBaseActivity<ResetPwdContract.View, ResetPwdPresenter> implements ResetPwdContract.View {

    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.et_new_pwd)
    EditText mEtNewPWD;
    @BindView(R.id.et_confirm_pwd)
    EditText mEtConfirmPWD;

    @OnClick(R.id.btn_submit)
    public void reSetPWD(){
        //重置密码
    }
    @Override
    protected int getLayoutId() {
        return R.layout.activity_reset_pwd;
    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        mTvTitle.setText("重置密码");
    }
}
