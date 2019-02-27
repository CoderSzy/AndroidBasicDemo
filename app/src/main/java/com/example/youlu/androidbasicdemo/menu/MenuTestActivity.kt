package com.example.youlu.androidbasicdemo.menu

import android.view.ActionMode
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.AbsListView
import android.widget.ArrayAdapter
import android.widget.ListView
import com.example.youlu.androidbasicdemo.R
import com.example.youlu.androidbasicdemo.base.BaseActivity
import com.example.youlu.androidbasicdemo.util.ToastUtil
import kotlinx.android.synthetic.main.activity_menu_test.*

class MenuTestActivity : BaseActivity() {

    private val mActionCallback = object : ActionMode.Callback {
        override fun onActionItemClicked(mode: ActionMode?, item: MenuItem?): Boolean {
            when (item?.itemId) {
                R.id.menu_delete -> {
                    return true
                }
                else -> {
                    return false
                }
            }
        }

        override fun onCreateActionMode(mode: ActionMode?, menu: Menu?): Boolean {
            mode?.menuInflater?.inflate(R.menu.menu_context_mode, menu)
            return true
        }

        override fun onPrepareActionMode(mode: ActionMode?, menu: Menu?): Boolean {
            return true
        }

        override fun onDestroyActionMode(mode: ActionMode?) {
            mActionMode = null
        }

    }

    private val mMultiChoiceListener = object : AbsListView.MultiChoiceModeListener {
        override fun onActionItemClicked(mode: ActionMode?, item: MenuItem?): Boolean {
            when (item?.itemId) {
                R.id.menu_delete -> {
                    ToastUtil.showToast(this@MenuTestActivity, "delete!")
                    return true
                }
                else -> {
                    return false
                }
            }

        }

        override fun onItemCheckedStateChanged(mode: ActionMode?, position: Int, id: Long, checked: Boolean) {
        }

        override fun onCreateActionMode(mode: ActionMode?, menu: Menu?): Boolean {
            mode?.menuInflater?.inflate(R.menu.menu_context_mode, menu)
            return true
        }

        override fun onPrepareActionMode(mode: ActionMode?, menu: Menu?): Boolean {
            toolbar_menu.visibility = View.GONE
            return true
        }

        override fun onDestroyActionMode(mode: ActionMode?) {
            toolbar_menu.visibility = View.VISIBLE
        }

    }

    private var mActionMode: ActionMode? = null

    override fun getLayoutId(): Int {
        return R.layout.activity_menu_test
    }

    override fun initView() {
        toolbar_menu.setTitle(R.string.title_menu)
        setSupportActionBar(toolbar_menu)
        val adapter = ArrayAdapter.createFromResource(this, R.array.data_time, android.R.layout.simple_expandable_list_item_1)
        lv_menu.adapter = adapter
    }

    override fun initListener() {
        tv_subject.setOnLongClickListener {
            if (mActionMode != null) {
                false
            } else {
                mActionMode = startActionMode(mActionCallback)
                it.isSelected = true
                true
            }

        }
        lv_menu.choiceMode = ListView.CHOICE_MODE_MULTIPLE_MODAL
        lv_menu.setMultiChoiceModeListener(mMultiChoiceListener)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_options, menu)
        return true
    }
}
