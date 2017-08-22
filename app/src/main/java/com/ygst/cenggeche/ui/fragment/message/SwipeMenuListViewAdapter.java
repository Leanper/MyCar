package com.ygst.cenggeche.ui.fragment.message;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.ygst.cenggeche.R;

import java.util.List;

import cn.jpush.im.android.api.model.Conversation;
import cn.jpush.im.android.api.model.GroupInfo;
import cn.jpush.im.android.api.model.UserInfo;

/**
 * Created by Administrator on 2017/8/17.
 */

public class SwipeMenuListViewAdapter extends BaseAdapter {

    List<Conversation> mListConversation;
    Context mContext;

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
                    R.layout.item_list_app, null);
            new ViewHolder(convertView);
        }
        ViewHolder holder = (ViewHolder) convertView.getTag();
        Conversation item = mListConversation.get(position);
        if (item.getType().toString().equals("single")) {
            UserInfo userInfo = (UserInfo) item.getTargetInfo();
            holder.tv_type.setText("单人");
            holder.tv_name.setText(userInfo.getUserName());
        }
        if (item.getType().toString().equals("group")) {
            GroupInfo groupInfo = (GroupInfo) item.getTargetInfo();
            holder.tv_type.setText("群组");
            holder.tv_name.setText("群组id:" + groupInfo.getGroupID());
        }
        return convertView;
    }

    class ViewHolder {
        TextView tv_type;
        TextView tv_name;

        public ViewHolder(View view) {
            tv_type = (TextView) view.findViewById(R.id.tv_type);
            tv_name = (TextView) view.findViewById(R.id.tv_name);
            view.setTag(this);
        }
    }
}
