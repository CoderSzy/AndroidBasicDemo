package com.example.youlu.androidbasicdemo.util

import android.content.Context
import android.widget.Toast

/**
 * author: youlu
 * date:   2019/2/27
 * email: youluoyemu2011@126.com
 * desc:
 */
class ToastUtil {

    companion object {
        fun showToast(context: Context, msg: String) {
            Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
        }

        fun showToast(context: Context, resId: Int) {
            Toast.makeText(context, resId, Toast.LENGTH_SHORT).show()
        }
    }

}