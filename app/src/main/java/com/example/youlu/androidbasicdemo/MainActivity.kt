package com.example.youlu.androidbasicdemo

import android.content.Intent
import android.view.View
import com.example.youlu.androidbasicdemo.base.BaseActivity
import com.example.youlu.androidbasicdemo.camera.CameraTestActivity
import com.example.youlu.androidbasicdemo.ui.menu.MenuTestActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity(), View.OnClickListener {
    override fun getLayoutId(): Int {
        return R.layout.activity_main
    }

    override fun initView() {

    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btn_menu -> {
                val intentMenu = Intent(this, MenuTestActivity::class.java)
                startActivity(intentMenu)
            }
            R.id.btn_camera -> {
                val intentCamera = Intent(this, CameraTestActivity::class.java)
                startActivity(intentCamera)
            }
            else -> {
            }
        }
    }

    override fun initListener() {
        btn_menu.setOnClickListener(this)
        btn_camera.setOnClickListener(this)
    }
}
