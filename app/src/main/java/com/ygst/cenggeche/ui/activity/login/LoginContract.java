package com.ygst.cenggeche.ui.activity.login;

import com.ygst.cenggeche.bean.CodeBean;
import com.ygst.cenggeche.bean.LoginBean;
import com.ygst.cenggeche.mvp.BasePresenter;
import com.ygst.cenggeche.mvp.BaseView;

/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class LoginContract {
    public interface View extends BaseView{
        void getSMSCodeSuccess(CodeBean codeBean);

        void getSMSCodeError();

        void checkSMSCodeSuccess(LoginBean loginBean);

        void checkSMSCodeError();

    }

    interface Presenter extends BasePresenter<View> {
        void getSMSCode(String phone) throws Exception;

        void checkSMSCode(String phone, String code, String registration_id);

    }
}
