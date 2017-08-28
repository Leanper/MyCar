package com.ygst.cenggeche.ui.activity.friendinfo;


import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.Gallery;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.ViewSwitcher;

import com.ygst.cenggeche.R;
import com.ygst.cenggeche.mvp.MVPBaseActivity;


/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class FriendInfoActivity extends MVPBaseActivity<FriendInfoContract.View, FriendInfoPresenter> implements FriendInfoContract.View,AdapterView.OnItemSelectedListener, ViewSwitcher.ViewFactory {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_friend_info;
    }

    private ImageSwitcher is;
    private Gallery gallery;


    private Integer[] mImageIds = { R.drawable.b, R.drawable.c,
            R.drawable.d, R.drawable.f,};

    /*
     * (non-Javadoc)
     *
     * @see android.app.Activity#onCreate(android.os.Bundle)
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);

        is = (ImageSwitcher) findViewById(R.id.switcher);
        is.setFactory(this);

        is.setInAnimation(AnimationUtils.loadAnimation(this,
                android.R.anim.fade_in));
        is.setOutAnimation(AnimationUtils.loadAnimation(this,
                android.R.anim.fade_out));

        gallery = (Gallery) findViewById(R.id.gallery);

        gallery.setAdapter(new ImageAdapter(this,mImageIds));
        gallery.setOnItemSelectedListener(this);
    }

    @Override
    public View makeView() {
        ImageView i = new ImageView(this);
        i.setBackgroundColor(0xFF000000);
        i.setScaleType(ImageView.ScaleType.FIT_CENTER);
        i.setLayoutParams(new ImageSwitcher.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        return i;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position,
                               long id) {
        is.setImageResource(mImageIds[position]);

    }
    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        // TODO Auto-generated method stub

    }
}
