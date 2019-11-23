package com.depromeet.hackthon7th

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_task_detail.*

class TaskDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_task_detail)
        setSupportActionBar(toolbar_task_detail)
        setTitle(R.string.task_detail_title)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        initView()
    }

    private fun initView() {

    }

    companion object {

        fun getStartIntent(context: Context) = Intent(context, TaskDetailActivity::class.java)
    }
}