package com.depromeet.hackthon7th

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        startActivityForResult(TaskDetailActivity.getStartIntent(this), START_ADD_TASK)
    }
}
