package com.ygst.cenggeche.ui.activity.targetuserinfo;


import com.ygst.cenggeche.R;
import com.ygst.cenggeche.mvp.MVPBaseActivity;


/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class TargetUserInfoActivity extends MVPBaseActivity<TargetUserInfoContract.View, TargetUserInfoPresenter> implements TargetUserInfoContract.View {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_target_user_info;
    }
}
