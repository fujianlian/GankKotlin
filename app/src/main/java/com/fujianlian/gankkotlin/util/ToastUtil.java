package com.fujianlian.gankkotlin.util;

import android.widget.Toast;

public class ToastUtil {

    /**
     * 短暂显示Toast提示(来自res)
     **/
    public static void showToast(String msg) {
        Toast.makeText(Global.getApplicationContext(),msg,Toast.LENGTH_SHORT).show();
    }

}
