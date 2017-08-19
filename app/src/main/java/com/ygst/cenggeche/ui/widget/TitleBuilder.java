package com.ygst.cenggeche.ui.widget;//package com.yongche.merchant.ui.widget;
//
//import android.app.Activity;
//import android.text.TextUtils;
//import android.view.View;
//import android.widget.ImageView;
//import android.widget.TextView;
//
//import com.yongche.merchant.R;
//import com.zhy.autolayout.AutoFrameLayout;
//import com.zhy.autolayout.AutoRelativeLayout;
//
//
///**
// * Created by 芮靖林
// * on 2017/1/13 14:06.
// */
//
//public class TitleBuilder {
//
//    /**
//     * title栏根布局
//     */
//    private View titleView;
//
//    //左边的控件
//    private TextView left_textview;
//    private ImageView left_imageview;
//
//    //中间的控件
//    private ImageView middle_imageview;
//    private TextView middle_textView;
//
//    //右边的控件
//    private TextView right_textview;
//    private ImageView right_imageview;
//    private ImageView moseright_imageview;
//    private AutoFrameLayout selfView;
//    private AutoRelativeLayout normalView;
//
//    /**
//     * 第一种  初始化方式
//     * 这里是直接引用进文件的初始化方式
//     *
//     * @param context
//     */
//    public TitleBuilder(Activity context) {
//
//        titleView = context.findViewById(R.id.title_bar);
//
//        initTitleView(titleView);
//
//    }
//
//    /**
//     * 第二种初始化方式
//     * 这里是比如你用代码创建布局的时候使用
//     *
//     * @param view
//     */
//    public TitleBuilder(View view) {
//
//        titleView = view.findViewById(R.id.title_bar);
//
//        initTitleView(titleView);
//
//
//    }
//
//    private void initTitleView(View titleRootView) {
//
//        //默认的Title视图
//        normalView = (AutoRelativeLayout) titleRootView.findViewById(R.id.title_normalview);
//        //自定义的Ttile
//        selfView = (AutoFrameLayout) titleRootView.findViewById(R.id.title_selfview);
//
//        //左侧的ImageView 和 TextView
//        left_textview = (TextView) titleRootView.findViewById(R.id.title_left_textview);
//        left_imageview = (ImageView) titleRootView.findViewById(R.id.title_left_imageview);
//
//        //中间得ImageView 和 TextView
//        middle_textView = (TextView) titleRootView.findViewById(R.id.title_middle_textview);
//        middle_imageview = (ImageView) titleRootView.findViewById(R.id.title_middle_imageview);
//
//        //右侧得ImageView 和 TextView
//        right_textview = (TextView) titleRootView.findViewById(R.id.title_right_textview);
//        right_imageview = (ImageView) titleRootView.findViewById(R.id.title_right_imageview);
//        //最右侧的imageView
//        moseright_imageview = (ImageView) titleRootView.findViewById(R.id.title_moseright_imageview);
//    }
//
//    //设置Title栏的背景色
//    public TitleBuilder setTitleBackGroundColor(int resid) {
//        titleView.setBackgroundColor(resid);
//        return this;
//    }
//
//    //设置Title栏的背景色
//    public TitleBuilder setTitleBackGroundRes(int resid) {
//        titleView.setBackgroundResource(resid);
//        return this;
//    }
//
//    /**
//     * left
//     */
//    /**
//     * 图片按钮
//     *
//     * @param resId
//     * @return
//     */
//    public TitleBuilder setLeftImageRes(int resId) {
//
//        left_imageview.setVisibility(resId > 0 ? View.VISIBLE : View.GONE);
//        left_imageview.setImageResource(resId);
//
//        return this;
//    }
//
//    /**
//     * 左边文字按钮
//     *
//     * @param text
//     * @return
//     */
//    public TitleBuilder setLeftText(String text, int size) {
//
//        left_textview.setVisibility(TextUtils.isEmpty(text) ? View.GONE : View.VISIBLE);
//        left_textview.setText(text);
//        left_textview.setTextSize(size);
//
//        return this;
//    }
//
//
//    /**
//     * 设置Image中间的事件
//     */
//    public TitleBuilder setLeftImageListener(View.OnClickListener listener) {
//
//        if (left_imageview.getVisibility() == View.VISIBLE) {
//
//            left_imageview.setOnClickListener(listener);
//
//        }
//
//        return this;
//    }
//
//    /**
//     * 设置Image中间的事件
//     */
//    public TitleBuilder setLeftTextListener(View.OnClickListener listener) {
//
//        if (left_textview.getVisibility() == View.VISIBLE) {
//
//            left_textview.setOnClickListener(listener);
//
//        }
//
//        return this;
//    }
//
//
//    /**
//     * 中间
//     */
//    /**
//     * 图片按钮
//     *
//     * @param resId
//     * @return
//     */
//    public TitleBuilder setMiddleImageRes(int resId) {
//
//        middle_imageview.setVisibility(resId > 0 ? View.VISIBLE : View.GONE);
//        middle_imageview.setImageResource(resId);
//
//        return this;
//    }
//
//    /**
//     * 中间
//     *
//     * @param text
//     * @return
//     */
//    public TitleBuilder setMiddleText(String text, int size) {
//
//        middle_textView.setVisibility(TextUtils.isEmpty(text) ? View.GONE : View.VISIBLE);
//        middle_textView.setText(text);
//        if (size != 0)
//            middle_textView.setTextSize(size);
//
//        return this;
//    }
//
//    /**
//     * 设置Image中间的事件
//     */
//    public TitleBuilder setMiddleImageListener(View.OnClickListener listener) {
//
//        if (middle_imageview.getVisibility() == View.VISIBLE) {
//
//            middle_imageview.setOnClickListener(listener);
//
//        }
//
//        return this;
//    }
//
//    /**
//     * 设置text中间的事件
//     */
//    public TitleBuilder setMiddleTextListener(View.OnClickListener listener) {
//
//        if (middle_textView.getVisibility() == View.VISIBLE) {
//
//            middle_textView.setOnClickListener(listener);
//
//        }
//
//        return this;
//    }
//
//    /**
//     * right
//     */
//    /**
//     * 右边图片按钮
//     *
//     * @param resId
//     * @return
//     */
//    public TitleBuilder setRightImageRes(int resId) {
//
//        right_imageview.setVisibility(resId > 0 ? View.VISIBLE : View.GONE);
//        right_imageview.setImageResource(resId);
//
//        return this;
//    }
//
//    /**
//     * 最右边图片按钮
//     *
//     * @param resId
//     * @return
//     */
//    public TitleBuilder setMostRightImageRes(int resId) {
//
//        moseright_imageview.setVisibility(resId > 0 ? View.VISIBLE : View.GONE);
//        moseright_imageview.setImageResource(resId);
//
//        return this;
//    }
//
//    /**
//     * 右边文字按钮
//     *
//     * @param text
//     * @return
//     */
//    public TitleBuilder setRightText(String text, int size) {
//
//        right_textview.setVisibility(TextUtils.isEmpty(text) ? View.GONE : View.VISIBLE);
//        right_textview.setText(text);
//        right_textview.setTextSize(size);
//
//        return this;
//    }
//
//    /**
//     * 设置TextView右边的事件
//     */
//    public TitleBuilder setRightTextListener(View.OnClickListener listener) {
//
//        if (right_textview.getVisibility() == View.VISIBLE) {
//
//            right_textview.setOnClickListener(listener);
//
//        }
//
//        return this;
//    }
//
//    /**
//     * 设置Image右边的事件
//     */
//    public TitleBuilder setRightImageListener(View.OnClickListener listener) {
//
//        if (right_imageview.getVisibility() == View.VISIBLE) {
//
//            right_imageview.setOnClickListener(listener);
//
//        }
//
//        return this;
//    }
//
//    /**
//     * 设置Image最右边的事件
//     */
//    public TitleBuilder setMostRightImageListener(View.OnClickListener listener) {
//
//        if (moseright_imageview.getVisibility() == View.VISIBLE) {
//
//            moseright_imageview.setOnClickListener(listener);
//
//        }
//
//        return this;
//    }
//
//    //创建自定义的一个Title
//    public TitleBuilder createSelfTitleView(View selfTitle) {
//        if (selfTitle != null) {
//            selfView.addView(selfTitle);
//        }
//        selfView.setVisibility(selfTitle == null ? View.GONE : View.VISIBLE);
//        normalView.setVisibility(selfTitle == null ? View.VISIBLE : View.GONE);
//        return this;
//    }
//
//
//    public View build() {
//        return titleView;
//    }
//}
