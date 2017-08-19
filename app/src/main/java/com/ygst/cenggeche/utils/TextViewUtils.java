package com.ygst.cenggeche.utils;

import android.widget.EditText;
import android.widget.TextView;

/**
 * @author :
 * @date :   2017/1/15
 */
public class TextViewUtils {

    public static String getText(TextView view) {
        return view.getText().toString().trim();
    }
     public  static String getText(EditText editText){
         return editText.getText().toString().trim();
     }

}
