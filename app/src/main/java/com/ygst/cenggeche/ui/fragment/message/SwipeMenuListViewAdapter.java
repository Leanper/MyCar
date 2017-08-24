package com.ygst.cenggeche.ui.fragment.message;

import android.content.Context;
import android.graphics.Bitmap;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.blankj.utilcode.utils.LogUtils;
import com.ygst.cenggeche.R;
import com.ygst.cenggeche.ui.view.XCRoundImageView;

import java.util.List;

import cn.jmessage.android.uikit.chatting.utils.TimeFormat;
import cn.jpush.im.android.api.callback.GetAvatarBitmapCallback;
import cn.jpush.im.android.api.content.CustomContent;
import cn.jpush.im.android.api.content.PromptContent;
import cn.jpush.im.android.api.content.TextContent;
import cn.jpush.im.android.api.enums.ConversationType;
import cn.jpush.im.android.api.model.Conversation;
import cn.jpush.im.android.api.model.GroupInfo;
import cn.jpush.im.android.api.model.Message;
import cn.jpush.im.android.api.model.UserInfo;

/**
 * Created by Administrator on 2017/8/17.
 */

public class SwipeMenuListViewAdapter extends BaseAdapter {

    List<Conversation> mListConversation;
    Context mContext;
    String lastTime;
    ViewHolder holder;

    public SwipeMenuListViewAdapter(Context context, List<Conversation> list) {
        mContext = context;
        this.mListConversation = list;
    }

    @Override
    public int getCount() {
        return mListConversation.size();
    }

    @Override
    public Object getItem(int position) {
        return mListConversation.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = View.inflate(mContext.getApplicationContext(),
                    R.layout.item_list_conversation, null);
            new ViewHolder(convertView);
        }
        holder = (ViewHolder) convertView.getTag();

        Conversation convItem = mListConversation.get(position);
        Message lastMsg = convItem.getLatestMessage();
        String contentStr = getLastMag(lastMsg);

//        String contentStr = ((TextContent) lastMsg.getContent()).getText();

        UserInfo userInfo = (UserInfo) convItem.getTargetInfo();
        LogUtils.i("SwipeMenuListViewAdapter", "会话名称：" + convItem.getTitle());
        //获取头像
        setHeadIcon(convItem);
        //会话的名称
        holder.mTVtargetName.setText(convItem.getTitle());
        holder.mTVlatestMessage.setText(contentStr);
        //会话界面时间
        holder.mTVlastTime.setText(lastTime);
        if (convItem.getType().equals(ConversationType.single)) {

        } else {
            GroupInfo groupInfo = (GroupInfo) convItem.getTargetInfo();
        }
        return convertView;
    }


    private String getLastMag(Message lastMsg) {
        String contentStr = "";
        if (lastMsg != null) {
            TimeFormat timeFormat = new TimeFormat(mContext, lastMsg.getCreateTime());
            lastTime = timeFormat.getTime();
            switch (lastMsg.getContentType()) {
                case image:
                    contentStr = mContext.getString(R.string.type_picture);
                    break;
                case voice:
                    contentStr = mContext.getString(R.string.type_voice);
                    break;
                case location:
                    contentStr = mContext.getString(R.string.type_location);
                    break;
                case file:
                    String extra = lastMsg.getContent().getStringExtra("video");
                    if (!TextUtils.isEmpty(extra)) {
                        contentStr = mContext.getString(R.string.type_smallvideo);
                    } else {
                        contentStr = mContext.getString(R.string.type_file);
                    }
                    break;
                case video:
                    contentStr = mContext.getString(R.string.type_video);
                    break;
                case eventNotification:
                    contentStr = mContext.getString(R.string.group_notification);
                    break;
                case custom:
                    CustomContent customContent = (CustomContent) lastMsg.getContent();
                    Boolean isBlackListHint = customContent.getBooleanValue("blackList");
                    if (isBlackListHint != null && isBlackListHint) {
                        contentStr = mContext.getString(R.string.jmui_server_803008);
                    } else {
                        contentStr = mContext.getString(R.string.type_custom);
                    }
                    break;
                case prompt:
                    contentStr = ((PromptContent) lastMsg.getContent()).getPromptText();
                    break;
                default:
                    contentStr = ((TextContent) lastMsg.getContent()).getText();
            }

        }
        return contentStr;
    }

    private void setHeadIcon(Conversation convItem) {
        if (convItem.getType().equals(ConversationType.single)) {
            UserInfo mUserInfo = (UserInfo) convItem.getTargetInfo();
            if (mUserInfo != null) {
                //显示性别
                if (mUserInfo.getGender().equals(UserInfo.Gender.female)) {
                    holder.mIVgender.setImageResource(R.mipmap.icon_girl);
                } else if (mUserInfo.getGender().equals(UserInfo.Gender.female)) {
                    holder.mIVgender.setImageResource(R.mipmap.icon_boy);
                } else {
                }
                //显示头像
                mUserInfo.getAvatarBitmap(new GetAvatarBitmapCallback() {
                    @Override
                    public void gotResult(int status, String desc, Bitmap bitmap) {
                        if (status == 0) {
                            holder.mIVavatar.setImageBitmap(bitmap);
                        } else {
                            holder.mIVavatar.setImageResource(R.drawable.jmui_head_icon);
                        }
                    }
                });
            } else {
                holder.mIVavatar.setImageResource(R.drawable.jmui_head_icon);
            }
        } else {
            GroupInfo mGroupInfo = (GroupInfo) convItem.getTargetInfo();
            holder.mIVavatar.setImageResource(R.drawable.group);
        }

    }

    class ViewHolder {

        //头像
        XCRoundImageView mIVavatar;
        //性别
        ImageView mIVgender;
        //目标用户名称
        TextView mTVtargetName;
        //最新消息
        TextView mTVlatestMessage;
        //最后会话时间
        TextView mTVlastTime;

        public ViewHolder(View view) {
            mIVavatar = (XCRoundImageView) view.findViewById(R.id.iv_avatar);
            mIVgender = (ImageView) view.findViewById(R.id.iv_gender);
            mTVtargetName = (TextView) view.findViewById(R.id.tv_target_name);
            mTVlatestMessage = (TextView) view.findViewById(R.id.tv_latest_message);
            mTVlastTime = (TextView) view.findViewById(R.id.tv_last_time);
            view.setTag(this);
        }
    }
}
