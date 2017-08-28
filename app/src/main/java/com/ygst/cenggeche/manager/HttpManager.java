package com.ygst.cenggeche.manager;


import com.ygst.cenggeche.app.MyApplication;
import com.ygst.cenggeche.interfaces.ProjectAPI;
import com.ygst.cenggeche.utils.RSAUtil;
import com.ygst.cenggeche.utils.RetrofitUtil;

import java.io.File;
import java.util.Iterator;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * w网络请求的工具类
 */

public class HttpManager {

    private String TAG = "HttpManager";

    public static HttpManager httpManager = new HttpManager();

    private HttpManager() {

    }


    public static HttpManager getHttpManager() {
        return httpManager;
    }


    /**
     * get方式请求
     * 封装时，传递observer
     *
     * @param url
     * @param observer
     */
    public void getMethod(String url, Observer<String> observer) {
        //获取被观察者
        Observable<String> observable = RetrofitUtil.getInstance().get2(ProjectAPI.class).getMethod(url);
        //在子线程中执行请求，在主线程观察，将信息设置给观察者
        observable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(observer);

    }

    /**
     * Post方式请求
     * 封装时，传递observer
     *
     * @param url
     * @param observer
     */
    public void postMethod(String url, Observer<String> observer, Map map) {
        String deviceId = "";
        String uid = "";
        if (MyApplication.getAndroidId() != null) {
            deviceId = MyApplication.getAndroidId();
        }
        if (MyApplication.getUid() != null) {
            uid = MyApplication.getUid();
        }
        String os="android";
        String sign =getSign(map);
        Observable<String> observable = RetrofitUtil.getInstance().get(ProjectAPI.class).postMethod(deviceId,uid,sign,url, map);
        //在子线程中执行请求，在主线程观察，将信息设置给观察者
        observable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(observer);
    }

    private String getSign(Map map){
        String stringA = "";
        //遍历list得到map里面排序后的元素
        Iterator<Map.Entry<String, String>> it = map.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<String, String> entry = it.next();
            stringA = stringA+entry.getKey()+"="+entry.getValue()+"&";
        }
        stringA = stringA.substring(0,stringA.length()-1);
        return RSAUtil.encryptByPublic(MyApplication.getContext(),stringA);
    }
    /**
     * Post方式请求
     * 封装时，传递observer
     *
     * @param url
     * @param observer
     */
    public void postMethod2(String url, Observer<String> observer, Map map) {
        if (MyApplication.getToken() != null)
            map.put("accessToken", MyApplication.getToken());
        if (MyApplication.getUid() != null)
            map.put("userId", MyApplication.getUid());
        if (MyApplication.getAndroidId() != null) {
            map.put("deviceId", MyApplication.getAndroidId());
        }
        map.put("os", "android");
        Observable<String> observable = RetrofitUtil.getInstance().get(ProjectAPI.class).postMethod(url, map);


        //在子线程中执行请求，在主线程观察，将信息设置给观察者
        observable.subscribeOn(Schedulers.io()).

                observeOn(AndroidSchedulers.mainThread()).

                subscribe(observer);

    }

    /**
     * 上传单张图片
     */
    public void upLoadIcon(String url, String path, Map map, String picname, Observer<ResponseBody> observer) {
        File file = new File(path);
        RequestBody requestbody = RequestBody.create(MediaType.parse("image/jpg"), file);
        RequestBody assboy = RequestBody.create(MediaType.parse("text/plain"), MyApplication.getToken());
        RequestBody userboy = RequestBody.create(MediaType.parse("text/plain"), MyApplication.getUid());
        RequestBody dvice_id = RequestBody.create(MediaType.parse("text/plain"), MyApplication.getAndroidId());
        RequestBody os = RequestBody.create(MediaType.parse("text/plain"), "android");
        MultipartBody.Part body = MultipartBody.Part.createFormData(picname, file.getName(), requestbody);
        Observable<ResponseBody> responseBodyObservable;
        if (map != null) {
            responseBodyObservable = RetrofitUtil.getInstance().get(ProjectAPI.class).upLoadImg(url, body, assboy, dvice_id, os, userboy, map);
        } else {
            responseBodyObservable = RetrofitUtil.getInstance().get(ProjectAPI.class).upLoadImg(url, body, assboy, dvice_id, os, userboy);
        }
        responseBodyObservable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(observer);
    }

}
