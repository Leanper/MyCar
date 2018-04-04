package com.ygst.cenggeche.ui.fragment.nearby;


import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

;
import com.ygst.cenggeche.R;
import com.ygst.cenggeche.mvp.MVPBaseFragment;
import com.ygst.cenggeche.ui.activity.nearbyuserinfo.NearByUserInfoActivity;
import com.ygst.cenggeche.utils.CommonUtils;
import com.ygst.cenggeche.utils.ToastUtil;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class NearbyFragment extends MVPBaseFragment<NearbyContract.View, NearbyPresenter> implements NearbyContract.View {

    @BindView(R.id.gv_nearby)
    GridView mNearByGridView;
    private View mNearByView;
    private String TAG=this.getClass().getSimpleName();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mNearByView = inflater.inflate(R.layout.fragment_nearby, container, false);
        ButterKnife.bind(this,mNearByView);
        initData();

        Log.i(TAG,"===");
        return mNearByView;
    }

    private void initData() {
        ArrayList<String> list = new ArrayList<>();
        for (int i=0;i<20;i++)
            list.add(i+"");
        NearByShowAdapter mNearAdapter=new NearByShowAdapter(getActivity(),list);
        mNearByGridView.setAdapter(mNearAdapter);
        mPresenter.getnearBy("888","105.56682644314236","26.99763156467014",1);
        mNearByGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                CommonUtils.startActivity(getActivity(), NearByUserInfoActivity.class);
            }
        });

    }


    @Override
    public void getnearbySuccess() {

    }

    @Override
    public void getnearbyFail(String msg) {
        ToastUtil.show(getActivity(),msg);

    }
}
