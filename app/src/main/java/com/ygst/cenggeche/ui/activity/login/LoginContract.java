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

        //检验账号未注册，不可登录，需先注册
        void unregistered();
        //检验账号已注册，可直接登录
        void registered();

        void getSMSCodeSuccess(CodeBean codeBean);

        void getSMSCodeError();

        void loginSuccess(LoginBean loginBean);

        void loginError();

    }

    interface Presenter extends BasePresenter<View> {

        //检验账号是否注册
        void checkIsRegist(String username);
        //获取验证码
        void getSMSCode(String phone) throws Exception;
        //登录
        void login(LoginBean loginBean,String checkType);

    }
}
