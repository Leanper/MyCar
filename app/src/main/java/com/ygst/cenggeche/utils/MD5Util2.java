package com.ygst.cenggeche.utils;

import android.util.Base64;

import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

/**
 * Created by Administrator on 2017/7/7.
 */

public class MD5Util2 {

    public static String getHmac_Md5(String key, String datas) {
        String reString = "";

        try {
            byte[] data = key.getBytes("UTF-8");
            // 根据给定的字节数组构造一个密钥,第二参数指定一个密钥算法的名称
            SecretKey secretKey = new SecretKeySpec(data, "hmacmd5");
            // 生成一个指定 Mac 算法 的 Mac 对象
            Mac mac = Mac.getInstance("hmacmd5");
            // 用给定密钥初始化 Mac 对象
            mac.init(secretKey);
            byte[] text = datas.getBytes("UTF-8");
            // 完成 Mac 操作
            byte[] text1 = mac.doFinal(text);
            reString = Base64.encodeToString(text1, Base64.NO_WRAP);

        } catch (Exception e) {
        }
        System.out.println("MD5加密： "+reString);
        return reString;
    }
}
