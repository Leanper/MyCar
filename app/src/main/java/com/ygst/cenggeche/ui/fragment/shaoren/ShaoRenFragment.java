package com.ygst.cenggeche.ui.fragment.shaoren;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ygst.cenggeche.R;
import com.ygst.cenggeche.mvp.MVPBaseFragment;
import com.ygst.cenggeche.ui.activity.releaseplan.ReleaseplanActivity;
import com.ygst.cenggeche.ui.activity.suretravel.SureTravelActivity;
import com.ygst.cenggeche.ui.activity.travelinfo.TravelInfoActivity;
import com.ygst.cenggeche.ui.fragment.cengche.MyViewPagerAdapter;
import com.ygst.cenggeche.ui.view.ZoomOutPageTransformer;
import com.ygst.cenggeche.utils.CommonUtils;
import com.ygst.cenggeche.utils.ToastUtil;

import java.util.ArrayList;


import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class ShaoRenFragment extends MVPBaseFragment<ShaoRenContract.View, ShaoRenPresenter> implements ShaoRenContract.View {
    private ViewPager mViewPager;
    private View mShaoRenView;
    @BindView(R.id.iv_trip_info)
    ImageView ivTripInfo;
    @BindView(R.id.iv_release_plan)
    ImageView ivReleasePlan;
    @BindView(R.id.iv_take_her)
    ImageView ivTakeHer;

    @OnClick({R.id.iv_take_her,R.id.iv_release_plan,R.id.iv_trip_info})
    public void onclick(View v){
        switch (v.getId()){
            case R.id.iv_release_plan:
                CommonUtils.startActivity(getActivity(), ReleaseplanActivity.class);
                break;
            case R.id.iv_take_her:
                Intent intent=new Intent(getActivity(),SureTravelActivity.class);
                intent.putExtra("CARTYPE",2+"");
                startActivity(intent);

                break;
            case R.id.iv_trip_info:
                CommonUtils.startActivity(getActivity(), TravelInfoActivity.class);

                break;
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        mShaoRenView = inflater.inflate(R.layout.fragment_shaoren, container, false);
        initView();
        ButterKnife.bind(this,mShaoRenView);

        return mShaoRenView;
    }

    private void initView() {
        mViewPager = (ViewPager) mShaoRenView.findViewById(R.id.tab_viewpager);
        mViewPager.setPageTransformer(true, new ZoomOutPageTransformer());
        mViewPager.setPageMargin(getResources().getDimensionPixelSize(R.dimen.pager_margin));
        ArrayList<String> strings = new ArrayList<>();
        strings.add("2");
        MyViewPagerAdapter mAdapter = new MyViewPagerAdapter(getActivity(),strings);
        mViewPager.setAdapter(mAdapter);
        mViewPager.setOffscreenPageLimit(mAdapter.getCount());
    }

}
