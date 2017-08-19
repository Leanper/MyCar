package com.ygst.cenggeche.utils;

import android.os.SystemClock;

import com.ygst.cenggeche.R;

/**
 * Created by admin on 2017/8/19.
 */

public class ClickUtils {

    public static long[] mHits;

    /**
     * number为几就是连击几次
     * @param number
     * @return
     */
    public static boolean numberClick(int number) {
        mHits = new long[number];
        System.arraycopy(mHits, 1, mHits, 0, mHits.length - 1);
        mHits[mHits.length - 1] = SystemClock.uptimeMillis();//获取手机开机时间
        if (mHits[mHits.length - 1] - mHits[0] < 500) {
            /**双击的业务逻辑*/
            return true;
        }
        return false;
    }
}
