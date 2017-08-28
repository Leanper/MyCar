package com.ygst.cenggeche.app;

import android.app.Application;
import android.content.Context;
import android.graphics.Point;
import android.os.Build;
import android.os.Handler;
import android.os.Process;
import android.os.StrictMode;
import android.support.multidex.MultiDex;
import android.util.Log;
import android.view.WindowManager;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.jarek.imageselect.core.SDCardStoragePath;
import com.jarek.imageselect.utils.SDCardUtils;
import com.lqr.emoji.IImageLoader;
import com.lqr.emoji.LQREmotionKit;
import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiskCache;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.core.download.BaseImageDownloader;
import com.ygst.cenggeche.utils.CommonUtils;
import com.ygst.cenggeche.utils.SharedPreferencesUtils;

import java.io.File;
import java.io.IOException;

import cn.jpush.im.android.api.JMessageClient;


/**
 * @author :   FeyTsien
 * @date :   2017/8/15
 */

public class MyApplication extends Application {

    public static String deviceId;
    public static String os = "android";
    public static String sign;

    private static final String IS_LOGIN_ED = "isLoginEd";
    private static final String IS_NOTIFICATION = "isNotification";
    private static Context mContext = null;

    public static int getMainThreadId() {
        return mainThreadId;
    }

    public static Handler getHandler() {
        return handler;
    }

    /**
     * 查询是否登陆
     *
     * @return
     */
    public static boolean isLoginEd() {
        return SharedPreferencesUtils.getBoolean(IS_LOGIN_ED, false);
    }

    /**
     * 设置是否登陆
     *
     * @param isLoginEd
     */
    public static void setIsLoginEd(boolean isLoginEd) {
        SharedPreferencesUtils.saveBoolean(IS_LOGIN_ED, isLoginEd);
    }

    /**
     * 查询是否勾选不再提示（开始通知栏权限时）
     *
     * @return
     */
    public static boolean isNotification() {
        return SharedPreferencesUtils.getBoolean(IS_NOTIFICATION, false);
    }

    /**
     * 设置是否勾选不再提示（开始通知栏权限时）
     *
     * @param isLoginEd
     */
    public static void setIsNotification(boolean isLoginEd) {
        SharedPreferencesUtils.saveBoolean(IS_NOTIFICATION, isLoginEd);
    }

    /**
     * 保存UserId
     */
    public static void saveUid(String username) {
        SharedPreferencesUtils.saveString("UID", username);
    }
    /*
    获取UserId
     */
    public static String getUid() {
        return SharedPreferencesUtils.getString("UID", null);
    }

    /**
     * 保存UserName
     */
    public static void saveUserName(String username) {
        SharedPreferencesUtils.saveString("USERNAME", username);
    }
    /*
    获取UserName
     */
    public static String getUserName() {
        return SharedPreferencesUtils.getString("USERNAME", null);
    }

    /**
     * 保存token
     */
    public static void saveToken(String token) {
        SharedPreferencesUtils.saveString("TOKEN", token);
    }
    /*
    获取token
     */
    public static String getToken() {
        return SharedPreferencesUtils.getString("TOKEN", null);
    }

    /**
     * 保存头像
     */
    public static void saveIcon(String token) {
        SharedPreferencesUtils.saveString("ICON", token);
    }
    /*
    获取头像
     */
    public static String getIcon() {
        return SharedPreferencesUtils.getString("ICON", null);
    }

    /**
     * 保存手机设备ID
     */
    public static void saveAndroidId() {
        SharedPreferencesUtils.saveString("ANDROID_ID", CommonUtils.getIMEI(MyApplication.getContext()));
    }

    /*
    获取手机设备ID
     */
    public static String getAndroidId() {
        return SharedPreferencesUtils.getString("ANDROID_ID", null);
    }

    /**
     * 保存RegistrationId
     */
    public static void saveRegistrationId(String userId) {
        SharedPreferencesUtils.saveString("REGISTRATION_ID", userId);
    }

    /**
     * 获取RegistrationId
     */
    public static String getRegistrationId() {
        return SharedPreferencesUtils.getString("REGISTRATION_ID", "");
    }

    // 保存验证状态
    public static void savaStatus(String time) {
        SharedPreferencesUtils.saveString("STATTUS", time);
    }

    public static String getStatus() {
        return SharedPreferencesUtils.getString("STATTUS", "0");
    }

    //清除登陆
    public static void clearLogin() {
        Log.i("clearLogin", "isTokenExpired: +++++++++++++++++++++++++++++++0005");
        SharedPreferencesUtils.saveString("USER_ID", "");
        SharedPreferencesUtils.saveBoolean(IS_LOGIN_ED, false);
        setIsLoginEd(false);

        saveToken("");
        saveIcon("");
        savaStatus("");
    }

    private static Handler handler;
    private static int mainThreadId;

    public static Context getContext() {
        return mContext;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
            StrictMode.setVmPolicy(builder.build());
        }
        System.out.println("获取context：etApplicationContext()");
        mContext = getApplicationContext();
        handler = new Handler();//创建Handle
        mainThreadId = Process.myTid();//得到主线程id
        saveAndroidId();
        // autolayout 的适配初始化 包括状态栏和底部操作栏
//        AutoLayoutConifg.getInstance().useDeviceSize().init(this);

        //极光配置
        //您可以在开发状态中启用调试模式。当发布时，您应该关闭调试模式.
        JMessageClient.setDebugMode(true);
        //init 初始化SDK
        JMessageClient.init(mContext);
        initImageLoader(this);
        //表情包开发
        LQREmotionKit.init(this, new IImageLoader() {
            @Override
            public void displayImage(Context context, String path, ImageView imageView) {
                Glide.with(context).load(path).centerCrop().diskCacheStrategy(DiskCacheStrategy.SOURCE).into(imageView);
            }
        });
    }


    public final static float DESIGN_WIDTH = 750; //绘制页面时参照的设计图宽度
    /**
     * 屏幕适配的处理（暂时没有使用，以后有时间研究）
     */
    public void resetDensity(){
        Point size = new Point();
        ((WindowManager)getSystemService(WINDOW_SERVICE)).getDefaultDisplay().getSize(size);

        getResources().getDisplayMetrics().xdpi = size.x/DESIGN_WIDTH*72f;
    }

    /**
     * <br>
     * This configuration tuning is custom. You can tune every option, you may
     * tune some of them, or you can create default configuration by
     * ImageLoaderConfiguration.createDefault(this); method. </br>
     *开源项目Android-Universal-Image-Loader的初始化
     * @param context Context
     */
    private void initImageLoader(Context context) {
        try {
            SDCardUtils.createFolder(SDCardStoragePath.DEFAULT_IMAGE_CACHE_PATH);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(
                context).threadPriority(Thread.NORM_PRIORITY - 2)
                .denyCacheImageMultipleSizesInMemory()
                .diskCache(new UnlimitedDiskCache(new File(SDCardStoragePath.DEFAULT_IMAGE_CACHE_PATH)))
//				.discCache(DiscCacheFactory.create(context, SDCardStoragePath.DEFAULT_IMAGE_CACHE_PATH))
                .memoryCacheSizePercentage(8)
                .memoryCacheExtraOptions(480, 800)
                .tasksProcessingOrder(QueueProcessingType.LIFO)
                .imageDownloader(new BaseImageDownloader(context))
                .writeDebugLogs()
                .build();
        // Initialize ImageLoader with configuration.
        ImageLoader.getInstance().init(config);
    }

    /**
     * 解决java.lang.NoClassDefFoundError错误，方法数超过65536了
     * 5.0以下系统会出次问题
     * @param base
     */
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(base);
    }
}
