package com.example.youlu.androidbasicdemo.base

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import com.example.youlu.androidbasicdemo.R
import com.example.youlu.androidbasicdemo.util.ToastUtil

/**
 * author: youlu
 * date:   2019/2/27
 * email: youluoyemu2011@126.com
 * desc:
 */

private const val CODE_CAMERA = 100

abstract class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutId())
        requestPermission()
        initView()
        initListener()
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if (grantResults.isNotEmpty()) {
            when (requestCode) {
                CODE_CAMERA -> {
                    if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                        ToastUtil.showToast(this, R.string.msg_permission_grant)
                    }
                }
                else -> {
                }
            }
        }
    }

    private fun requestPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.CAMERA), CODE_CAMERA)
        }
    }

    abstract fun getLayoutId(): Int

    abstract fun initView()

    abstract fun initListener()
}