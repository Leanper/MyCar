package com.ygst.cenggeche.ui.activity.addfriend;


import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.ygst.cenggeche.R;
import com.ygst.cenggeche.mvp.MVPBaseActivity;
import com.ygst.cenggeche.utils.JMessageUtils;
import com.ygst.cenggeche.utils.ToastUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.jpush.im.android.api.ContactManager;
import cn.jpush.im.api.BasicCallback;


/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class AddFriendActivity extends MVPBaseActivity<AddFriendContract.View, AddFriendPresenter> implements AddFriendContract.View {
    private String username;

    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.et_reason)
    EditText mEditTextReason;

    @BindView(R.id.bt_submit)
    Button mBtnSubmit;

    @OnClick(R.id.bt_submit)
    public void addFriendSubmit(){
        Intent intent = this.getIntent();
        String username=intent.getStringExtra(JMessageUtils.TARGET_USERNAME);
        String reason = mEditTextReason.getText().toString();
        ContactManager.sendInvitationRequest(username, JMessageUtils.TARGET_APP_KEY, reason, new BasicCallback() {
            @Override
            public void gotResult(int responseCode, String responseMessage) {
                if (0 == responseCode) {
                    //好友请求请求发送成功
                    ToastUtil.show(AddFriendActivity.this,"申请好友发送成功，等待对方回应");
                } else {
                    //好友请求发送失败
                    ToastUtil.show(AddFriendActivity.this,"申请好友发送失败");
                }
            }
        });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_add_friends;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        mTvTitle.setText("加为好友");

    }

}
