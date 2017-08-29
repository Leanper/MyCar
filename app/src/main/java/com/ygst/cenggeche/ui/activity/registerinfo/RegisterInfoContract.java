package com.ygst.cenggeche.ui.activity.registerinfo;

import com.ygst.cenggeche.mvp.BasePresenter;
import com.ygst.cenggeche.mvp.BaseView;

import java.util.Map;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class RegisterInfoContract {
    interface View extends BaseView {
        void registrationSuccess();
        void registrationError();
    }

    interface  Presenter extends BasePresenter<View> {
        //注册确认
       void registrationConfirm(Map<String, String> map);
    }
}
