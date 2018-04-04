package com.ygst.cenggeche.ui.activity.releaseplan.cartype;

import android.app.ProgressDialog;
import android.util.Log;

import com.blankj.utilcode.utils.LogUtils;
import com.google.gson.Gson;
import com.ygst.cenggeche.bean.AllCarBrandBean;
import com.ygst.cenggeche.bean.CodeBean;
import com.ygst.cenggeche.manager.HttpManager;
import com.ygst.cenggeche.mvp.BasePresenterImpl;
import com.ygst.cenggeche.utils.CommonUtils;
import com.ygst.cenggeche.utils.ToastUtil;
import com.ygst.cenggeche.utils.UrlUtils;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import rx.Observer;


/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class CartypePresenter extends BasePresenterImpl<CartypeContract.View> implements CartypeContract.Presenter{

    private String TAG=this.getClass().getSimpleName();

    @Override
    public void getCarBrand() {}
//
//        final ProgressDialog progressDialog = CommonUtils.showProgressDialog(mView.getContext(), "获取信息");
//        Map<String, String> map = new HashMap<>();
//        map.put("s","1");
//
//        HttpManager.getHttpManager().postMethod(UrlUtils.BASEURl+UrlUtils.GETALLCARBRAND, new Observer<String>() {
//
//            @Override
//            public void onCompleted() {
//            }
//
//            @Override
//            public void onError(Throwable e) {
//                progressDialog.dismiss();
//                ToastUtil.show(mView.getContext(), "请求失败，请重试");
//                LogUtils.e(TAG, "返回的onError", e);
//                Log.i(TAG,e.toString());
//            }
//
//            @Override
//            public void onNext(String s) {
//                progressDialog.dismiss();
//                LogUtils.i("HttpManager", "ssss:" + s);
//                Gson gson = new Gson();
//                CodeBean codeBean = gson.fromJson(s, CodeBean.class);
//                AllCarBrandBean allCarBrandBean = gson.fromJson(s, AllCarBrandBean.class);
//                if ("0000".equals(codeBean.getCode())) {
//
//                    List<AllCarBrandBean.BrandBean> data = allCarBrandBean.getBrand();
//                    Collections.sort(data, new Comparator<AllCarBrandBean.BrandBean>() {
//                        @Override
//                        public int compare(AllCarBrandBean.BrandBean lhs, AllCarBrandBean.BrandBean rhs) {
//                            if (lhs.getInitials().equals(rhs.getInitials())) {
//                                return lhs.getBrand().compareTo(rhs.getBrand());
//                            } else {
//                                if ("#".equals(lhs.getInitials())) {
//                                    return 1;
//                                } else if ("#".equals(rhs.getInitials())) {
//                                    return -1;
//                                }
//                                return lhs.getInitials().compareTo(rhs.getInitials());
//                            }
//                        }
//                    });
//                    CityPickController cityPickController = new CityPickController(CartypeActivity.this, view, data);
//                    cityPickController.setCallBack(new CityPickController.CxCallBack() {
//
//                        @Override
//                        public void cxCallBack(String carname, String cartype, String log) {
////                            CommonUtils.jumptActivity(CarPpCxActivity.this, CarBaseInFoActivity.class, "carname", carname, "cartype", cartype, "logo", log);
//                        }
//                    });
//
//
//                    //已注册，可以登录
//                    if (mView != null) {
//                        mView.getCarBrandSuccess();
//                    }
//                } else {
//                    if (mView != null) {
//                        mView.getCarBrandFail();
//                    }
//                }
//            }
//        }, map);
//
//    }

    @Override
    public void getCarTypeBrand() {

    }
}
