package com.example.youlu.androidbasicdemo.ui.search

import android.app.SearchManager
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.youlu.androidbasicdemo.R
import com.example.youlu.androidbasicdemo.util.ToastUtil

class SearchableActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_searchable)
        if (Intent.ACTION_SEARCH == intent.action) {
            intent.getStringExtra(SearchManager.QUERY)?.also {
                ToastUtil.showToast(this, it)
            }
        }
    }
}
