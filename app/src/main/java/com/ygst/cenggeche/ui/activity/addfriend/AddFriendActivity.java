package com.ygst.cenggeche.ui.activity.addfriend;


import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.EditText;

import com.ygst.cenggeche.R;
import com.ygst.cenggeche.mvp.MVPBaseActivity;
import com.ygst.cenggeche.utils.JChatUtils;
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

    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    @BindView(R.id.et_target_userid)
    EditText mEditTextTargetUserId;

    @BindView(R.id.et_reason)
    EditText mEditTextReason;

    @BindView(R.id.bt_submit)
    Button mBtnSubmit;

    @OnClick(R.id.bt_submit)
    public void addFriendSubmit(){
        String userID= mEditTextTargetUserId.getText().toString();
        String reason = mEditTextReason.getText().toString();
        ContactManager.sendInvitationRequest(userID, JChatUtils.TARGET_APP_KEY, reason, new BasicCallback() {
            @Override
            public void gotResult(int responseCode, String responseMessage) {
                if (0 == responseCode) {
                    //好友请求请求发送成功
                    ToastUtil.show(AddFriendActivity.this,"添加好友发送成功");
                } else {
                    //好友请求发送失败
                    ToastUtil.show(AddFriendActivity.this,"添加好友失败");
                }
            }
        });
    }

    TextWatcher mTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            if (TextUtils.isEmpty(mEditTextTargetUserId.getText().toString())) {
                //设置按钮不可点击
                mBtnSubmit.setClickable(false);
                //设置按钮为按下状态
                mBtnSubmit.setPressed(false);
            } else {
                //设置按钮可点击
                mBtnSubmit.setClickable(true);
                //设置按钮为正常状态
                mBtnSubmit.setPressed(true);
            }
        }

        @Override
        public void afterTextChanged(Editable s) {
        }
    };

    @Override
    protected int getLayoutId() {
        return R.layout.activity_add_friends;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        setToolBar(mToolbar, "添加好友", true);
        initData();
    }

    private void initData() {
        //设置提交按钮是否可以点击
        mEditTextTargetUserId.addTextChangedListener(mTextWatcher);
    }
}
