package com.depromeet.hackthon7th

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import com.depromeet.hackthon7th.ext.checkLengthAndShowMessage
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
        et_task_title.addTextChangedListener { text ->
            text?.let {
                et_task_title.checkLengthAndShowMessage(layout_task_title, text.toString())
            }
        }

        et_task_desc.addTextChangedListener { text ->
            text?.let {
                et_task_desc.checkLengthAndShowMessage(layout_task_desc, text.toString())
            }
        }
    }

    private fun checkAvailable(): Boolean {
        val checkTitle = et_task_title.checkLengthAndShowMessage(
            layout_task_title,
            et_task_title.text.toString()
        )
        val checkDesc = et_task_desc.checkLengthAndShowMessage(
            layout_task_desc,
            et_task_desc.text.toString()
        )

        return checkTitle && checkDesc
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_task, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> finish()
            R.id.menu_finish -> if (checkAvailable()) finish()
        }

        return true
    }

    companion object {

        fun getStartIntent(context: Context) = Intent(context, TaskDetailActivity::class.java)
    }
}