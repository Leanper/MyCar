package com.ygst.cenggeche.ui.activity.nearbyuserinfo;


import com.ygst.cenggeche.R;
import com.ygst.cenggeche.mvp.MVPBaseActivity;


/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class NearByUserInfoActivity extends MVPBaseActivity<NearByUserInfoContract.View, NearByUserInfoPresenter> implements NearByUserInfoContract.View {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_nearbyuserinfo;
    }
}
