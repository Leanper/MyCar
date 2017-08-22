package com.ygst.cenggeche.ui.fragment.message;

import com.ygst.cenggeche.mvp.BasePresenterImpl;

import cn.jpush.im.android.api.JMessageClient;
import cn.jpush.im.android.api.enums.ConversationType;
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
        if (conversation.getType().equals(ConversationType.single)){
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
