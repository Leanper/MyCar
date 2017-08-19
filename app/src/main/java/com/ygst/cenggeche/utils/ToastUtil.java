package com.ygst.cenggeche.utils;

import android.content.Context;
import android.view.Gravity;
import android.widget.Toast;

public class ToastUtil {

    public static void show(Context context, String content) {
        Toast toast = Toast.makeText(context, content, Toast.LENGTH_SHORT);
        // 设置吐司的展示位置
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }

    public static void show(Context context, int contentId) {
        Toast toast = Toast.makeText(context, contentId, Toast.LENGTH_SHORT);
        // 设置吐司的展示位置
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }
}