package com.example.youlu.androidbasicdemo.camera

import android.Manifest
import android.content.pm.PackageManager
import android.graphics.BitmapFactory
import android.hardware.Camera
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.view.View
import android.widget.FrameLayout
import android.widget.Toast
import com.example.youlu.androidbasicdemo.R
import com.example.youlu.androidbasicdemo.base.BaseActivity
import com.example.youlu.androidbasicdemo.util.ToastUtil
import kotlinx.android.synthetic.main.activity_camera_test.*


class CameraTestActivity : BaseActivity(), View.OnClickListener {

    private var mCamera: Camera? = null
    private var mPreview: CameraPreview? = null
    private val mPictureCallback = Camera.PictureCallback { data, _ ->
        val bitmap = BitmapFactory.decodeByteArray(data, 0, data.size)
        iv_result.setImageBitmap(bitmap)

    }

    override fun initView() {
        mCamera = getCameraInstance()
        mPreview = mCamera?.let {
            CameraPreview(this, it)
        }
        mPreview.also {
            val preview: FrameLayout = findViewById(R.id.fl_preview)
            preview.addView(it)
        }
    }

    override fun onResume() {
        super.onResume()
        if (mCamera == null) {
            mCamera = getCameraInstance()
            mPreview?.setCamera(mCamera)
        }
    }

    override fun onPause() {
        super.onPause()
        stopPreviewAndReleaseCamera()
    }

    override fun initListener() {
        btn_capture.setOnClickListener(this)
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_camera_test
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btn_capture -> {
                capturePhoto()
            }
            else -> {
            }
        }
    }


    private fun getCameraInstance(): Camera? {
        if (checkCameraHardware()) {
            return try {
                Camera.open()
            } catch (e: Exception) {
                null
            }
        } else {
            Toast.makeText(this, R.string.hint_no_camera, Toast.LENGTH_SHORT).show()
            finish()
            return null
        }
    }

    private fun checkCameraHardware() =
            packageManager.hasSystemFeature(PackageManager.FEATURE_CAMERA)


    private fun capturePhoto() {
        mCamera?.takePicture(null, null, mPictureCallback)
    }

    private fun stopPreviewAndReleaseCamera() {
        mCamera?.apply {
            stopPreview()
            release()
            mCamera = null
        }
    }
}
