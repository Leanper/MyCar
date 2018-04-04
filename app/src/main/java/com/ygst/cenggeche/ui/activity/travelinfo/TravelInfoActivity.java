package com.ygst.cenggeche.ui.activity.travelinfo;


import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.ygst.cenggeche.R;
import com.ygst.cenggeche.mvp.MVPBaseActivity;
import com.ygst.cenggeche.utils.ToastUtil;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class TravelInfoActivity extends MVPBaseActivity<TravelInfoContract.View, TravelInfoPresenter> implements TravelInfoContract.View {


    @BindView(R.id.ll_travelinfo)
    ListView mInfoListView;
    @BindView(R.id.tv_title)
    TextView mTvTitle;

    /**
     * 返回
     */
    @OnClick(R.id.iv_back)
    public void goBack(){
        finish();
    }


    @Override
    protected int getLayoutId() {
        return R.layout.activity_travelinfo;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        mTvTitle.setText("北京市");
        initData();


    }

    private void initData() {
        ArrayList<String> list=new ArrayList<>();
        for (int i=0;i<10;i++)
            list.add("0");
        TravelInfoAdapter mAdapter=new TravelInfoAdapter(this,list);
        mInfoListView.setAdapter(mAdapter);

        mAdapter.setmOnItemClickListener(new TravelInfoAdapter.OnItemClickListener(){

            @Override
            public void onItemClick(View view, int postion) {

            }

            @Override
            public void onItemDeleteClick(View view, int postion) {
                ToastUtil.show(TravelInfoActivity.this,"删除某条消息");
            }

            @Override
            public void onItemWaitClick(View view, int postion) {
                ToastUtil.show(TravelInfoActivity.this,"等待某条消息");
            }

            @Override
            public void onItemLongClick(View view, int postion) {
                ToastUtil.show(TravelInfoActivity.this,"长按某条消息");
            }
        });

//

    }
}
