package com.ygst.cenggeche.manager;


import android.text.TextUtils;

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
        if (MyApplication.getUserId() != null) {
            uid = MyApplication.getUserId();
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
        if (MyApplication.getUserId() != null)
            map.put("userId", MyApplication.getUserId());
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
        RequestBody userboy = RequestBody.create(MediaType.parse("text/plain"), MyApplication.getUserId());
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

    /**
     * 上传多张图片以及文件
     */

    public void upLoadImgs(String url, String[] file, Observer<ResponseBody> observer) {
        RequestBody assboy = RequestBody.create(MediaType.parse("text/plain"), MyApplication.getToken());
        RequestBody userboy = RequestBody.create(MediaType.parse("text/plain"), MyApplication.getUserId());
        RequestBody dvice_id = RequestBody.create(MediaType.parse("text/plain"), MyApplication.getAndroidId());
        RequestBody os = RequestBody.create(MediaType.parse("text/plain"), "android");
        RequestBody id = RequestBody.create(MediaType.parse("text/plain"), MyApplication.getUpCarId());
        MultipartBody.Part pic1body = null;
        if (!TextUtils.isEmpty(file[0])) {
            RequestBody requestbody = RequestBody.create(MediaType.parse("image/jpg"), new File(file[0]));
            pic1body = MultipartBody.Part.createFormData("pic1", file[0], requestbody);
        }
        MultipartBody.Part pic2body = null;
        if (!TextUtils.isEmpty(file[1])) {
            RequestBody requestbody2 = RequestBody.create(MediaType.parse("image/jpg"), new File(file[1]));
            pic2body = MultipartBody.Part.createFormData("pic2", file[1], requestbody2);
        }
        MultipartBody.Part pic3body = null;
        if (!TextUtils.isEmpty(file[2])) {
            RequestBody requestbody3 = RequestBody.create(MediaType.parse("image/jpg"), new File(file[2]));
            pic3body = MultipartBody.Part.createFormData("pic3", file[2], requestbody3);
        }
        MultipartBody.Part pic4body = null;
        if (!TextUtils.isEmpty(file[3])) {
            RequestBody requestbody4 = RequestBody.create(MediaType.parse("image/jpg"), new File(file[3]));
            pic4body = MultipartBody.Part.createFormData("pic4", file[3], requestbody4);
        }
        MultipartBody.Part pic5body = null;
        if (!TextUtils.isEmpty(file[4])) {
            RequestBody requestbody5 = RequestBody.create(MediaType.parse("image/jpg"), new File(file[4]));
            pic5body = MultipartBody.Part.createFormData("pic5", file[4], requestbody5);
        }
        MultipartBody.Part pic6body = null;
        if (!TextUtils.isEmpty(file[5])) {
            RequestBody requestbody6 = RequestBody.create(MediaType.parse("image/jpg"), new File(file[5]));
            pic6body = MultipartBody.Part.createFormData("pic6", file[5], requestbody6);
        }
        MultipartBody.Part pic7body = null;
        if (!TextUtils.isEmpty(file[6])) {
            RequestBody requestbody7 = RequestBody.create(MediaType.parse("image/jpg"), new File(file[6]));
            pic7body = MultipartBody.Part.createFormData("pic7", file[6], requestbody7);
        }

        MultipartBody.Part pic8body = null;
        if (!TextUtils.isEmpty(file[7])) {
            RequestBody requestbody8 = RequestBody.create(MediaType.parse("image/jpg"), new File(file[7]));
            pic8body = MultipartBody.Part.createFormData("pic8", file[7], requestbody8);
        }
        MultipartBody.Part pic9body = null;
        if (!TextUtils.isEmpty(file[8])) {
            RequestBody requestbody9 = RequestBody.create(MediaType.parse("image/jpg"), new File(file[8]));
            pic9body = MultipartBody.Part.createFormData("pic9", file[8], requestbody9);
        }


        MultipartBody.Part pic10body = null;
        if (!TextUtils.isEmpty(file[9])) {
            RequestBody requestbody8 = RequestBody.create(MediaType.parse("image/jpg"), new File(file[9]));
            pic10body = MultipartBody.Part.createFormData("pic10", file[9], requestbody8);
        }
        MultipartBody.Part pic11body = null;
        if (!TextUtils.isEmpty(file[10])) {
            RequestBody requestbody9 = RequestBody.create(MediaType.parse("image/jpg"), new File(file[10]));
            pic11body = MultipartBody.Part.createFormData("pic11", file[10], requestbody9);
        }
        Observable<ResponseBody> observable = RetrofitUtil.getInstance().get(ProjectAPI.class).upLoadsImgs(url, assboy, userboy, dvice_id, os, id, pic1body, pic2body, pic3body, pic4body, pic5body, pic6body, pic7body, pic8body, pic9body, pic10body, pic11body);
        observable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(observer);
    }
}
