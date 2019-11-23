package com.depromeet.hackthon7th

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import com.depromeet.hackthon7th.database.*
import com.depromeet.hackthon7th.ext.Toaster
import com.depromeet.hackthon7th.ext.checkLengthAndShowMessage
import com.depromeet.hackthon7th.util.getCurrentDateTime
import kotlinx.android.synthetic.main.activity_task_detail.*
import java.util.*


class TaskDetailActivity : AppCompatActivity() {
    private var priority = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_task_detail)
        setSupportActionBar(toolbar_task_detail)
        setTitle(R.string.task_detail_label)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        initView()
    }

    private fun initView() {
        val priorityBtns = arrayOf(btn_task_priority1, btn_task_priority2, btn_task_priority3)
        val priorityOrange = arrayOf(
            R.drawable.ic_priority_orange1,
            R.drawable.ic_priority_orange2,
            R.drawable.ic_priority_orange3
        )
        val priorityYellow = arrayOf(
            R.drawable.ic_priority_yellow1,
            R.drawable.ic_priority_yellow2,
            R.drawable.ic_priority_yellow3
        )

        et_task_date.setText(getCurrentDateTime())
        et_task_date.setOnClickListener {

        }
        et_task_repeat.setOnClickListener {

        }
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
        cb_task_repeat.setOnClickListener {
            val isChecked = cb_task_repeat.isChecked
            et_task_repeat.visibility =
                if (isChecked) View.VISIBLE else View.INVISIBLE

            priorityBtns.forEachIndexed { i, btn ->
                val btnDrawable =
                    getDrawable(if (isChecked) priorityOrange[i] else priorityYellow[i])
                btn.setCompoundDrawablesWithIntrinsicBounds(null, btnDrawable, null, null)
            }
        }

        priorityBtns.forEach { btn ->
            btn.setOnClickListener {
                if (priorityBtns.indexOf(btn) != priority) {
                    btn.apply {
                        background = getDrawable(R.drawable.back_rank_btn)
                        this.setTypeface(btn.typeface, Typeface.BOLD)
                    }

                    priorityBtns[priority].apply {
                        this.setBackgroundColor(Color.TRANSPARENT)
                        this.typeface = Typeface.DEFAULT
                    }

                    priority = priorityBtns.indexOf(btn)
                }
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

    private fun addTaskIntoDatabase() {
        val title = et_task_title.text.toString()
        val desc = et_task_desc.text.toString()
        val newTask = Task(title, desc, TaskType.ONCE, Date(), Date(), Date(), Priority.HIGH)

        TaskUtil.addTask(newTask, object : RealmCallback {
            override fun onSuccess() {
                setResult(ADD_TASK_SUCCESS)
                finish()
            }

            override fun onError(error: Throwable?) {
                Toaster.showShort(R.string.task_detail_error)
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_task, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> finish()
            R.id.menu_finish -> if (checkAvailable()) addTaskIntoDatabase()
        }

        return true
    }

    companion object {

        fun getStartIntent(context: Context) = Intent(context, TaskDetailActivity::class.java)
    }
}