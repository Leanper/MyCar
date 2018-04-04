package com.ygst.cenggeche.ui.fragment.cengche;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.blankj.utilcode.utils.LogUtils;
import com.ygst.cenggeche.R;
import com.ygst.cenggeche.mvp.MVPBaseFragment;
import com.ygst.cenggeche.ui.activity.releaseplan.ReleaseplanActivity;
import com.ygst.cenggeche.ui.activity.suretravel.SureTravelActivity;
import com.ygst.cenggeche.ui.activity.travelinfo.TravelInfoActivity;
import com.ygst.cenggeche.ui.fragment.me.MeFragment;
import com.ygst.cenggeche.ui.view.ZoomOutPageTransformer;
import com.ygst.cenggeche.utils.CommonUtils;
import com.ygst.cenggeche.utils.ToastUtil;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static android.content.ContentValues.TAG;
import static com.ygst.cenggeche.R.id.rl_cengche;


/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class CengCheFragment extends MVPBaseFragment<CengCheContract.View, CengChePresenter> implements CengCheContract.View{

    private View mCengCheView;
    private ViewPager mViewPager;
    @BindView(R.id.iv_trip_info)
    ImageView ivTripInfo;
    @BindView(R.id.iv_release_plan)
    ImageView ivReleasePlan;
    @BindView(R.id.iv_take_her)
    ImageView ivTakeHer;
    private String TAG="CengCheFragment";
    private ArrayList<String> list;
    private MyViewPagerAdapter mAdapter;
    private int flage=-1;

    @OnClick({R.id.iv_take_her,R.id.iv_release_plan,R.id.iv_trip_info})
    public void onclick(View v){
        switch (v.getId()){
            case R.id.iv_release_plan:
                CommonUtils.startActivity(getActivity(), ReleaseplanActivity.class);
                break;
            case R.id.iv_take_her:

                Intent intent=new Intent(getActivity(),SureTravelActivity.class);
                intent.putExtra("CARTYPE",1+"");
                startActivity(intent);

                break;
            case R.id.iv_trip_info:
                CommonUtils.startActivity(getActivity(), TravelInfoActivity.class);

                break;
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mCengCheView = inflater.inflate(R.layout.fragment_cengche, container, false);
        ButterKnife.bind(this,mCengCheView);

        initView();
        mPresenter.getAllinfo(1,1+"");

        return mCengCheView;
    }

    //初始化控件
    private void initView() {
        mViewPager = (ViewPager) mCengCheView.findViewById(R.id.tab_viewpager);

        mViewPager.setPageTransformer(true, new ZoomOutPageTransformer());
        mViewPager.setPageMargin(getResources().getDimensionPixelSize(R.dimen.pager_margin));
        list = new ArrayList<>();
        for (int  i=0;i<5;i++)
            list.add("---");
        mAdapter = new MyViewPagerAdapter(getActivity(),list);
        mViewPager.setAdapter(mAdapter);
        mViewPager.setOffscreenPageLimit(mAdapter.getCount());

        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                Log.i(TAG,position+"onPageScrolled+++++");
                if(position==list.size()-1){
                    for (int  i=0;i<5;i++)
                        list.add("---");
                    mViewPager.setAdapter(mAdapter);
                }
            }

            @Override
            public void onPageSelected(int position) {
                Log.i(TAG,position+"position+++++");
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                Log.i(TAG,state+"state+++++");
            }
        });

//        s.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                int currentItem = mViewPager.getCurrentItem();
//                ToastUtil.show(getActivity(),currentItem+"==惦记的");
//            }
//        });


    }

    @Override
    public void getAllInfoSuccess() {
        Log.i(TAG,"sss");
    }

    @Override
    public void getAllInfoFail() {
        Log.i(TAG,"fails");
    }
}
