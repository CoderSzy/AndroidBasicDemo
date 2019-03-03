package com.example.youlu.androidbasicdemo.dialog

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.support.v4.app.DialogFragment
import com.example.youlu.androidbasicdemo.R

/**
 * author: youlu
 * date:   2019/3/3
 * email: youluoyemu2011@126.com
 * desc:
 */

private const val ARG_MSG = "msg"

class HintDialogFragment : DialogFragment() {

    private var mMsg: String? = null
    private var mInactionListener: OnFragmentInactionListener? = null

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if (context is OnFragmentInactionListener) {
            mInactionListener = context
        } else {
            throw ClassCastException("${context.toString()} must implement OnFragmentInactionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        mInactionListener = null
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments.let {
            mMsg = it?.getString(ARG_MSG)
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = AlertDialog.Builder(activity)
        return builder.setMessage(mMsg)
                .setPositiveButton(R.string.btn_dialog_ok) { _, _ ->
                    mInactionListener?.onBtnPositiveClick()
                }
                .setNegativeButton(R.string.btn_dialog_cancel) { _, _ ->
                    mInactionListener?.onBtnNegativeClick()
                }
                .create()
    }

    interface OnFragmentInactionListener {
        fun onBtnPositiveClick()
        fun onBtnNegativeClick()
    }

    companion object {

        @JvmStatic
        fun newInstance(msg: String) =
                HintDialogFragment().apply {
                    arguments = Bundle().apply {
                        putString(ARG_MSG, msg)
                    }
                }

    }
}