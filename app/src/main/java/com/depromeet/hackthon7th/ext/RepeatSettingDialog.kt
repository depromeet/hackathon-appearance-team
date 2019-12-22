package com.depromeet.hackthon7th.ext

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import com.depromeet.hackthon7th.R

class RepeatSettingDialog(context: Context) : Dialog(context) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dialog_repeat_picker)

        initView()
    }

    private fun initView() {

    }
}