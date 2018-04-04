package com.ygst.cenggeche.ui.activity.travelinfo;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.ygst.cenggeche.R;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/8/25.
 */

public class TravelInfoAdapter extends BaseAdapter {

    private  ArrayList<String> mList;
    private  Context context;
    private OnItemClickListener mOnItemClickListener;

    public TravelInfoAdapter(Context context, ArrayList<String> list){
        this.context=context;
        this.mList=list;
    }
    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder = null;

        if (convertView == null) {
            convertView = View.inflate(context, R.layout.travelinfo_item, null);
            viewHolder = new ViewHolder();
            viewHolder.mTvCengorshao = (TextView) convertView.findViewById(R.id.tv_cengorshao);
            viewHolder.mTvNoteDate = (TextView) convertView.findViewById(R.id.tv_note_date);
            viewHolder.mTvReleaseDate = (TextView) convertView.findViewById(R.id.tv_release_date);
            viewHolder.mTvStartLocation = (TextView) convertView.findViewById(R.id.tv_start_location);
            viewHolder.mTvEndLocation = (TextView) convertView.findViewById(R.id.tv_end_location);
            viewHolder.mTvRemarks = (TextView) convertView.findViewById(R.id.tv_remarks);
            viewHolder.mTvWaitCengche = (TextView) convertView.findViewById(R.id.tv_wait_cengche);
            viewHolder.mIvDelete = (ImageView) convertView.findViewById(R.id.iv_delete);


            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        //删除点击事件
        viewHolder.mIvDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mOnItemClickListener.onItemDeleteClick(v,position);
            }
        });



//        holder.mLabel1.setTextSize(CommonUtils.px2dip(20));
//        holder.mLabel2.setTextSize(CommonUtils.px2dip(20));
//        holder.mLabel3.setTextSize(CommonUtils.px2dip(20));
        return convertView;
    }


    //View复用
    public final class ViewHolder {

        TextView mTvCengorshao;
        TextView mTvNoteDate;
        TextView mTvReleaseDate;
        TextView mTvStartLocation;
        TextView mTvEndLocation;
        TextView mTvRemarks;
        TextView mTvWaitCengche;
        ImageView mIvDelete;


    }

    public void setmOnItemClickListener(OnItemClickListener mOnItemClickListener) {
        this.mOnItemClickListener = mOnItemClickListener;
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int postion);

        void onItemDeleteClick(View view, int postion);
        void onItemWaitClick(View view, int postion);

        void onItemLongClick(View view, int postion);
    }

}
