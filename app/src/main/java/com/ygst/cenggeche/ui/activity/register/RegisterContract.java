package com.ygst.cenggeche.ui.activity.register;

import com.ygst.cenggeche.bean.CodeBean;
import com.ygst.cenggeche.mvp.BasePresenter;
import com.ygst.cenggeche.mvp.BaseView;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class RegisterContract {
    interface View extends BaseView {
        //检验账号未被注册，可以使用
        void checkIsRegistSuccess();
        //检验账号已被注册，不可使用
        void checkIsRegistError();
        //获取验证码成功
        void getSMSCodeSuccess(CodeBean codeBean);
        //获取验证码失败
        void getSMSCodeError();
        //验证码校验成功
        void checkSMSCodeSuccess(CodeBean codeBean);
        //验证码校验失败
        void checkSMSCodeError();
    }

    interface  Presenter extends BasePresenter<View> {
        //检验账号是否被注册
        void checkIsRegist(String username);
        //获取验证码
        void getSMSCode(String phone) throws Exception;
        //校验验证码
        void checkSMSCode(String username, String smsCode);
    }
}
