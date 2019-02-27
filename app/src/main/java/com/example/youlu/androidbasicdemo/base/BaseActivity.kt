package com.example.youlu.androidbasicdemo.base

import android.os.Bundle
import android.support.v7.app.AppCompatActivity

/**
 * author: youlu
 * date:   2019/2/27
 * email: youluoyemu2011@126.com
 * desc:
 */
abstract class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutId())
        initView()
        initListener()
    }

    abstract fun getLayoutId(): Int

    abstract fun initView()

    abstract fun initListener()
}