package com.ygst.cenggeche.ui.fragment.message;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.blankj.utilcode.utils.LogUtils;
import com.ygst.cenggeche.R;
import com.ygst.cenggeche.mvp.MVPBaseFragment;
import com.ygst.cenggeche.ui.view.swipemenulistview.SwipeMenu;
import com.ygst.cenggeche.ui.view.swipemenulistview.SwipeMenuCreator;
import com.ygst.cenggeche.ui.view.swipemenulistview.SwipeMenuItem;
import com.ygst.cenggeche.ui.view.swipemenulistview.SwipeMenuListView;
import com.ygst.cenggeche.utils.JChatUtils;
import com.ygst.cenggeche.utils.ToastUtil;

import java.util.List;

import butterknife.ButterKnife;
import cn.jmessage.android.uikit.chatting.ChatActivity;
import cn.jpush.im.android.api.JMessageClient;
import cn.jpush.im.android.api.model.Conversation;
import cn.jpush.im.android.api.model.GroupInfo;
import cn.jpush.im.android.api.model.UserInfo;

/**
 * Created by Administrator on 2017/8/16.
 */

public class MessageFragment extends MVPBaseFragment<MessageContract.View, MessagePresenter> implements MessageContract.View {
    private String TAG = "MessageFragment";
    private Activity mContext;
    private View mRootView;
    List<Conversation> mListConversation;

    //    private List<ApplicationInfo> mAppList;
    private AppAdapter mAdapter;
    private SwipeMenuListView mListView;
    private String mTargetId = "18601995150";
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        LogUtils.i(TAG, "------------onCreate");
        //生命周期2：onCreate()；
        super.onCreate(savedInstanceState);
        mContext = this.getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        LogUtils.i(TAG, "------------onCreateView");
        //生命周期3：onCreateView()；
        mRootView = inflater.inflate(R.layout.fragment_message, container, false);
        ButterKnife.bind(getActivity());
        initView();
        return mRootView;
    }


    private void initView() {

        mListConversation = JMessageClient.getConversationList();
        mListView = (SwipeMenuListView) mRootView.findViewById(R.id.listView);
        mAdapter = new AppAdapter();
        if (mListConversation != null) {
            mListView.setAdapter(mAdapter);
        }

        // step 1. create a MenuCreator
        SwipeMenuCreator creator = new SwipeMenuCreator() {

            @Override
            public void create(SwipeMenu menu) {
                // create "open" item
                SwipeMenuItem openItem = new SwipeMenuItem(getActivity().getApplicationContext());
                // set item background
                openItem.setBackground(new ColorDrawable(Color.rgb(0xC9, 0xC9,
                        0xCE)));
                // set item width
                openItem.setWidth(dp2px(90));
                // set item title
                openItem.setTitle("Open");
                // set item title fontsize
                openItem.setTitleSize(18);
                // set item title font color
                openItem.setTitleColor(Color.WHITE);
                // add to menu
                menu.addMenuItem(openItem);

                // create "delete" item
                SwipeMenuItem deleteItem = new SwipeMenuItem(getActivity().getApplicationContext());
                // set item background
                deleteItem.setBackground(new ColorDrawable(Color.rgb(0xF9,
                        0x3F, 0x25)));
                // set item width
                deleteItem.setWidth(dp2px(90));
                // set a icon
                deleteItem.setIcon(R.drawable.ic_delete);
                // add to menu
                menu.addMenuItem(deleteItem);
            }
        };
        // set creator
        mListView.setMenuCreator(creator);


        // set SwipeListener
        mListView.setOnSwipeListener(new SwipeMenuListView.OnSwipeListener() {

            @Override
            public void onSwipeStart(int position) {
                // swipe start
                LogUtils.i(TAG, "swipe start");
            }

            @Override
            public void onSwipeEnd(int position) {
                // swipe end
                LogUtils.i(TAG, "swipe start");
            }
        });
        // 左滑某一个Item
        mListView.setOnMenuItemClickListener(new SwipeMenuListView.OnMenuItemClickListener() {
            @Override
            public void onMenuItemClick(int position, SwipeMenu menu, int index) {
                Conversation item = mListConversation.get(position);
                switch (index) {
                    case 0:
                        // open
                        open(item);
                        break;
                    case 1:
                        // delete删除某个联系人会话
                        delete(item,position);
                        break;
                }
            }
        });
        //单点某一个Item
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                final Intent intent = new Intent();
                Conversation conversation = mListConversation.get(position);
                if (conversation.getType().toString().equals("single")) {
                    intent.putExtra(JChatUtils.TARGET_ID_KEY, conversation.getId());
                    intent.setClass(mContext, ChatActivity.class);
                    startActivity(intent);
                } else if (conversation.getType().toString().equals("group")) {
                    intent.putExtra(JChatUtils.GROUP_ID_KEY, conversation.getId());
                    intent.setClass(mContext, ChatActivity.class);
                    startActivity(intent);
                }
            }
        });

        // 长按某一个Item
        mListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view,
                                           int position, long id) {
//				Toast.makeText(getApplicationContext(), position + " long click",0).show();
                return false;
            }
        });

    }

    /**
     * 删除会话
     * @param conversation
     * @param position
     */
    private void delete(Conversation conversation,int position) {
        Boolean deleteBoolean = JMessageClient.deleteSingleConversation(conversation.getId(), "");
        if(deleteBoolean){
            mListConversation.remove(position);
            mAdapter.notifyDataSetChanged();
        }else{
            ToastUtil.show(getActivity(),"删除会话失败");
        }
    }

    private void open(Conversation item) {
    }

    class AppAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return mListConversation.size();
        }

        @Override
        public Conversation getItem(int position) {
            return mListConversation.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = View.inflate(getActivity().getApplicationContext(),
                        R.layout.item_list_app, null);
                new AppAdapter.ViewHolder(convertView);
            }
            AppAdapter.ViewHolder holder = (AppAdapter.ViewHolder) convertView.getTag();
            Conversation item = getItem(position);
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

    private int dp2px(int dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp,
                getResources().getDisplayMetrics());
    }
}
