package com.ygst.cenggeche.ui.fragment.message;

import com.ygst.cenggeche.mvp.BasePresenter;
import com.ygst.cenggeche.mvp.BaseView;

import cn.jpush.im.android.api.model.Conversation;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class MessageContract {
    interface View extends BaseView {

        void getDeleteConversationSuccess(String type,int position);

        void getDeleteConversationError();
    }

    interface  Presenter extends BasePresenter<View> {

       void deleteConversation(Conversation conversation, int position);
    }
}
