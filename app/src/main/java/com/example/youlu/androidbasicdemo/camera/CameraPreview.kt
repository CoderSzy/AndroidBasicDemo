package com.example.youlu.androidbasicdemo.camera

import android.content.Context
import android.hardware.Camera
import android.util.Log
import android.view.SurfaceHolder
import android.view.SurfaceView

/*****************************************************
 * 版权信息: 2018 北京振中电子技术有限公司版权所有
 * 创建作者: szy
 * 创建日期: 2019/2/27
 * ****************************************************
 * 更改记录: 更新人:    更新时间:    更新概要:
 *
 * ****************************************************
 *  类功能说明:
 * ****************************************************/
class CameraPreview(context: Context, private var mCamera: Camera?) : SurfaceView(context), SurfaceHolder.Callback {

    private val mHolder: SurfaceHolder = holder.apply {
        addCallback(this@CameraPreview)
    }

    override fun surfaceChanged(holder: SurfaceHolder?, format: Int, width: Int, height: Int) {
        if (mHolder.surface == null) {
            return
        }
        try {
            mCamera?.stopPreview()
        } catch (e: Exception) {

        }


        mCamera?.apply {


            try {
                setPreviewDisplay(holder)
                startPreview()
            } catch (e: Exception) {
                Log.d("TAG", "")
            }
        }

    }

    override fun surfaceDestroyed(holder: SurfaceHolder?) {
//        mCamera?.stopPreview()
    }

    override fun surfaceCreated(holder: SurfaceHolder?) {
        mCamera?.apply {
            try {
                parameters.apply {
                    parameters.setRotation(90)
                    requestLayout()
                }
                setPreviewDisplay(holder)
                startPreview()
            } catch (e: Exception) {
                Log.d("TAG", "")
            }
        }
    }

    fun setCamera(camera: Camera?) {
        mCamera = camera
    }


}