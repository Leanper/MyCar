package com.ygst.cenggeche.ui.fragment.cengche;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ygst.cenggeche.R;
import com.ygst.cenggeche.utils.CommonUtils;

import java.util.ArrayList;
import java.util.LinkedList;


/**
 * Created by Administrator on 2017/8/24.
 */

public class MyViewPagerAdapter extends PagerAdapter {

    private LinkedList<View> mViewCache = null;
    private  Context context;
    private ArrayList<String> list;
    public MyViewPagerAdapter(Context context, ArrayList<String> list){
        this.context=context;
        this.list=list;
        mViewCache = new LinkedList<>();
    }



    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }


    @Override
    public Object instantiateItem(ViewGroup container, int position) {
       ViewHolder holder = null;
        View convertView = null;
        if (mViewCache.size() == 0) {
            convertView = View.inflate(context, R.layout.item_viewpager, null);
            holder = new ViewHolder();
//            holder.ivPic = (ImageView) convertView.findViewById(R.id.iv_title_pic);
            holder.tvName = (TextView) convertView.findViewById(R.id.tv_exper_name);
//
            holder.tvEndLocation = (TextView) convertView.findViewById(R.id.tv_end_location);
            holder.tvStartLocation = (TextView) convertView.findViewById(R.id.tv_start_location);
            holder.tvUserName = (TextView) convertView.findViewById(R.id.tv_user_name);
            holder.tvReleaseDate = (TextView) convertView.findViewById(R.id.tv_release_date);
//            holder.vLine = convertView.findViewById(R.id.v_line);
            holder.tvName.setTextSize(CommonUtils.px2dip(24));
            holder.tvEndLocation.setTextSize(CommonUtils.px2dip(24));
            holder.tvStartLocation.setTextSize(CommonUtils.px2dip(24));
            holder.tvUserName.setTextSize(CommonUtils.px2dip(28));
            holder.tvReleaseDate.setTextSize(CommonUtils.px2dip(24));
            convertView.setTag(holder);
        } else {
            convertView = mViewCache.removeFirst();
            holder = (ViewHolder) convertView.getTag();
        }

//			/* 动态设置view 横线 让它和上方的文字等宽*/
        holder.tvName.measure(0, 0);
        int measuredWidth = holder.tvName.getMeasuredWidth();
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(measuredWidth, 1);
        params.addRule(RelativeLayout.BELOW, R.id.tv_exper_name);
        params.addRule(RelativeLayout.CENTER_HORIZONTAL);
        container.addView(convertView);

        //点击事件
        return convertView;
    }


    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
        mViewCache.add((View) object);
    }

    //View复用
    public final class ViewHolder {

        public TextView tvName;
        public TextView tvEndLocation,tvStartLocation,tvUserName,tvReleaseDate;
        public ImageView ivPic;
        public View vLine;
    }
}
