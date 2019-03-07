package com.example.youlu.androidbasicdemo.ui.menu

import android.app.SearchManager
import android.content.ComponentName
import android.content.Context
import android.support.v7.widget.SearchView
import android.view.*
import android.widget.*
import com.example.youlu.androidbasicdemo.R
import com.example.youlu.androidbasicdemo.base.BaseActivity
import com.example.youlu.androidbasicdemo.ui.dialog.HintDialogFragment
import com.example.youlu.androidbasicdemo.util.ToastUtil
import kotlinx.android.synthetic.main.activity_menu_test.*

private const val TAG_HINT_DIALOG = "hintDialog"

class MenuTestActivity : BaseActivity(), View.OnLongClickListener, HintDialogFragment.OnFragmentInactionListener {
    override fun onBtnPositiveClick() {
        val isRemoved = mDataSrc.removeAll(mDataFilter)
        if (isRemoved) {
            mAdapter.notifyDataSetChanged()
            mDataFilter.clear()
            ToastUtil.showToast(this, R.string.msg_menu_delete_success)
        }
    }

    override fun onBtnNegativeClick() {
        mDataFilter.clear()
        ToastUtil.showToast(this, R.string.msg_menu_delete_cancel)
    }

    override fun onLongClick(v: View?): Boolean {
        when (v?.id) {
            R.id.tv_subject -> {
                return if (mActionMode != null) {
                    false
                } else {
                    mActionMode = startActionMode(mActionCallback)
                    v.isSelected = true
                    true
                }
            }
            R.id.tv_context_menu -> {
                return false
            }
            R.id.tv_popup_menu -> {
                showPopupMenu(v)
                return true
            }
            else -> {
                return false
            }
        }
    }

    private val mActionCallback = object : ActionMode.Callback {
        override fun onActionItemClicked(mode: ActionMode?, item: MenuItem?): Boolean {
            return when (item?.itemId) {
                R.id.menu_delete -> {
                    true
                }
                else -> {
                    false
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

    private var hintDialog: HintDialogFragment? = null
    private val mMultiChoiceListener = object : AbsListView.MultiChoiceModeListener {
        override fun onActionItemClicked(mode: ActionMode?, item: MenuItem?): Boolean {
            return when (item?.itemId) {
                R.id.menu_delete -> {
                    if (hintDialog == null) {
                        hintDialog = HintDialogFragment.newInstance(getString(R.string.msg_ensure_delete, mDataFilter.size))
                    }
                    hintDialog?.show(supportFragmentManager, TAG_HINT_DIALOG)
                    true
                }
                else -> {
                    false
                }
            }
        }

        override fun onItemCheckedStateChanged(mode: ActionMode?, position: Int, id: Long, checked: Boolean) {
            if (checked) {
                mDataFilter.add(mDataSrc[position])
            } else {
                mDataFilter.remove(mDataSrc[position])
            }
            mode?.subtitle = getString(R.string.tv_menu_sub_title, mDataFilter.size)
        }

        override fun onCreateActionMode(mode: ActionMode?, menu: Menu?): Boolean {
            mode?.menuInflater?.inflate(R.menu.menu_context_mode, menu)
            mode?.setTitle(R.string.tv_menu_title)
            return true
        }

        override fun onPrepareActionMode(mode: ActionMode?, menu: Menu?): Boolean {
            return true
        }

        override fun onDestroyActionMode(mode: ActionMode?) {
        }

    }

    private var mActionMode: ActionMode? = null
    private val mDataSrc = mutableListOf<String>()
    private val mDataFilter = mutableListOf<String>()
    private lateinit var mAdapter: ArrayAdapter<String>

    override fun getLayoutId(): Int {
        return R.layout.activity_menu_test
    }

    override fun initView() {
        toolbar_menu.setTitle(R.string.title_menu)
        setSupportActionBar(toolbar_menu)
        mDataSrc.addAll(resources.getStringArray(R.array.data_time))
        mAdapter = ArrayAdapter(this, android.R.layout.simple_expandable_list_item_1, mDataSrc)
        lv_menu.adapter = mAdapter
    }

    override fun initListener() {
        registerForContextMenu(tv_context_menu)
        tv_subject.setOnLongClickListener(this)
        tv_popup_menu.setOnLongClickListener(this)
        lv_menu.choiceMode = ListView.CHOICE_MODE_MULTIPLE_MODAL
        lv_menu.setMultiChoiceModeListener(mMultiChoiceListener)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_options, menu)
        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        val menuItem = menu?.findItem(R.id.menu_search)
        val searchView = menuItem?.actionView as SearchView?
        searchView?.apply {
            setSearchableInfo(searchManager.getSearchableInfo(
                    ComponentName(this@MenuTestActivity,"com.example.youlu.androidbasicdemo.ui.search.SearchableActivity")))
//            setIconifiedByDefault(false)
            isSubmitButtonEnabled = true
            isQueryRefinementEnabled = true
        }
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when (item?.itemId) {
//            R.id.menu_search -> {
//                ToastUtil.showToast(this, R.string.item_search)
//                true
//            }
            R.id.menu_settings -> {
                ToastUtil.showToast(this, R.string.item_settings)
                true
            }
            else -> {
                super.onOptionsItemSelected(item)
            }
        }
    }

    override fun onCreateContextMenu(menu: ContextMenu?, v: View?, menuInfo: ContextMenu.ContextMenuInfo?) {
        super.onCreateContextMenu(menu, v, menuInfo)
        when (v?.id) {
            R.id.tv_context_menu -> {
                menuInflater.inflate(R.menu.menu_context, menu)
            }
            else -> {
            }
        }
    }

    override fun onContextItemSelected(item: MenuItem?): Boolean {
        return when (item?.itemId) {
            R.id.item_red -> {
                ToastUtil.showToast(this, R.string.item_red)
                item.isChecked = !item.isChecked
                true
            }
            R.id.item_green -> {
                ToastUtil.showToast(this, R.string.item_green)
                item.isChecked = !item.isChecked
                true
            }
            else -> {
                super.onContextItemSelected(item)
            }
        }
    }

    private fun showPopupMenu(view: View) {
        val popupMenu = PopupMenu(this, view)
        popupMenu.setOnMenuItemClickListener {
            return@setOnMenuItemClickListener when (it.itemId) {
                R.id.menu_search -> {
                    true
                }
                else -> {
                    false
                }
            }
        }
        popupMenu.inflate(R.menu.menu_options)
        popupMenu.show()
    }
}
