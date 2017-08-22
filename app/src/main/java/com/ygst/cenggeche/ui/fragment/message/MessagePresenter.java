package com.ygst.cenggeche.ui.fragment.message;

import com.ygst.cenggeche.mvp.BasePresenterImpl;
import com.ygst.cenggeche.utils.JChatUtils;

import cn.jpush.im.android.api.JMessageClient;
import cn.jpush.im.android.api.model.Conversation;
import cn.jpush.im.android.api.model.GroupInfo;
import cn.jpush.im.android.api.model.UserInfo;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class MessagePresenter extends BasePresenterImpl<MessageContract.View> implements MessageContract.Presenter{

    @Override
    public void deleteConversation(Conversation conversation, int position) {
        Boolean deleteBoolean;
        if (conversation.getType().toString().equals(JChatUtils.CONVERSATION_TYPE_SINGLE)){
            String targetId = ((UserInfo) conversation.getTargetInfo()).getUserName();
            deleteBoolean = JMessageClient.deleteSingleConversation(targetId, conversation.getTargetAppKey());
            if (deleteBoolean) {
                mView.getDeleteConversationSuccess("single",position);
            }else{
                mView.getDeleteConversationError();
            }
        }else{
            long groupID = ((GroupInfo) conversation.getTargetInfo()).getGroupID();
            deleteBoolean =JMessageClient.deleteGroupConversation(groupID);
            if (deleteBoolean) {
                mView.getDeleteConversationSuccess("group",position);
            }else{
                mView.getDeleteConversationError();
            }

        }
    }
}
