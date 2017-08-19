package com.ygst.cenggeche.ui.activity;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ygst.cenggeche.R;
import com.ygst.cenggeche.ui.activity.addfriend.AddFriendActivity;
import com.ygst.cenggeche.ui.activity.base.BaseActivity;
import com.ygst.cenggeche.ui.fragment.FirstFragment;
import com.ygst.cenggeche.ui.fragment.SanFragment;
import com.ygst.cenggeche.ui.fragment.message.MessageFragment;
import com.ygst.cenggeche.ui.test.ContactsActivity;
import com.ygst.cenggeche.utils.ToastUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.jiguang.api.JCoreInterface;

public class MainActivity extends BaseActivity{
    private TextView mTextMessage;
    private MessageFragment mMsgFragment;
    private FirstFragment mFirstFragment;
    private SanFragment mSanFragment;

    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.rl_main_title)
    RelativeLayout mRLayoutMainTitle;
    @BindView(R.id.tv_title)
    TextView mTextViewTitle;
    @BindView(R.id.iv_title_menu_left)
    ImageView mImageViewTitleMenuLeft;
    @BindView(R.id.iv_title_menu_right)
    ImageView mImageViewTitleMenuRight;

    @OnClick(R.id.iv_title_menu_left)
    public void titleMenuLeft(){
        Intent intent = new Intent();
        intent.setClass(this, AddFriendActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.iv_title_menu_right)
    public void titleMenuRight(){
        Intent intent = new Intent();
        intent.setClass(this, ContactsActivity.class);
        startActivity(intent);
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {

            FragmentManager fm = getFragmentManager();
            FragmentTransaction transaction = fm.beginTransaction();

            switch (item.getItemId()) {
                case R.id.navigation_home:
                    doubleClick(R.id.navigation_home);
                    mToolbar.setVisibility(View.GONE);
                    if (mFirstFragment == null) {
                        mFirstFragment = new FirstFragment();
                    }
                    // 使用当前Fragment的布局替代content的控件
                    transaction.replace(R.id.content, mFirstFragment);
                    break;
                case R.id.navigation_dashboard:
                    doubleClick(R.id.navigation_dashboard);
                    mToolbar.setVisibility(View.VISIBLE);
                    mRLayoutMainTitle.setVisibility(View.VISIBLE);
                    mImageViewTitleMenuLeft.setVisibility(View.VISIBLE);
                    mImageViewTitleMenuRight.setVisibility(View.VISIBLE);
                    mTextViewTitle.setText("消息");
                    if (mMsgFragment == null) {
                        mMsgFragment = new MessageFragment();
                    }
                    transaction.replace(R.id.content, mMsgFragment);
                    break;
                case R.id.navigation_notifications:
                    doubleClick(R.id.navigation_notifications);
                    mTextViewTitle.setText("我");
                    mImageViewTitleMenuLeft.setVisibility(View.GONE);
                    mImageViewTitleMenuRight.setVisibility(View.GONE);
                    if (mSanFragment == null) {
                        mSanFragment = new SanFragment();
                    }
                    transaction.replace(R.id.content, mSanFragment);
                    break;
            }
            // 事务提交
            transaction.commit();
            return true;
        }
    };

    private long[] mHits = new long[2];

    /**
     * 判断是否双击
     * @param itemId
     */
    private void doubleClick(int itemId) {
        System.arraycopy(mHits, 1, mHits, 0, mHits.length - 1);
        mHits[mHits.length - 1] = SystemClock.uptimeMillis();//获取手机开机时间
        if (mHits[mHits.length - 1] - mHits[0] < 500) { //间隔时间设置为500毫秒
            /**双击的业务逻辑*/
            switch (itemId) {
                case R.id.navigation_home:
                    ToastUtil.show(this, "蹭车双击");
                    break;
                case R.id.navigation_dashboard:
                    ToastUtil.show(this, "消息双击");
                    break;
                case R.id.navigation_notifications:
                    ToastUtil.show(this, "我的双击");
                    break;
            }
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        setToolBar(mToolbar, "", false);
//        JMessageClient.registerEventReceiver(this);
        initView();
        // 设置默认的Fragment
        setDefaultFragment();
    }

    @Override
    protected void onPause() {
        JCoreInterface.onPause(this);
        super.onPause();
    }

    @Override
    protected void onResume() {
        JCoreInterface.onResume(this);
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        JMessageClient.unRegisterEventReceiver(this);
    }

    private void initView() {
//        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }


    private void setDefaultFragment() {
        mToolbar.setVisibility(View.GONE);
        FragmentManager fm = getFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        mFirstFragment = new FirstFragment();
        transaction.replace(R.id.content, mFirstFragment);
        transaction.commit();
    }

}
