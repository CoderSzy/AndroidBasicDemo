package com.example.youlu.androidbasicdemo.menu

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.ActionMode
import android.view.Menu
import android.view.MenuItem
import android.widget.AbsListView
import android.widget.ArrayAdapter
import android.widget.ListView
import com.example.youlu.androidbasicdemo.R
import com.example.youlu.androidbasicdemo.base.BaseActivity
import kotlinx.android.synthetic.main.activity_menu_test.*

class MenuTestActivity : BaseActivity() {

    private val mMultiChoiceListener = object : AbsListView.MultiChoiceModeListener {
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

        override fun onItemCheckedStateChanged(mode: ActionMode?, position: Int, id: Long, checked: Boolean) {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

        override fun onCreateActionMode(mode: ActionMode?, menu: Menu?): Boolean {
            mode?.menuInflater?.inflate(R.menu.menu_context_mode, menu)
            return true
        }

        override fun onPrepareActionMode(mode: ActionMode?, menu: Menu?): Boolean {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

        override fun onDestroyActionMode(mode: ActionMode?) {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

    }

    override fun getLayoutId(): Int {
        return R.layout.activity_menu_test
    }

    override fun initView() {
        val adapter = ArrayAdapter.createFromResource(this, R.array.data_time, android.R.layout.simple_expandable_list_item_1)
        lv_menu.adapter = adapter
    }

    override fun initListener() {
        lv_menu.choiceMode = ListView.CHOICE_MODE_MULTIPLE_MODAL
        lv_menu.setMultiChoiceModeListener(mMultiChoiceListener)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_options, menu)
        return true
    }
}
