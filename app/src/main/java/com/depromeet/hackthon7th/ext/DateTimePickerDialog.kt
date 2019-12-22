package com.depromeet.hackthon7th.ext

import android.app.Dialog
import android.content.Context
import android.content.res.Resources
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import com.depromeet.hackthon7th.R
import kotlinx.android.synthetic.main.dialog_datepicker.*


class DateTimePickerDialog(
    context: Context,
    private val listener: () -> Unit
) : Dialog(context) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dialog_datepicker)
        initView()
    }

    private fun initView() {
        window?.setLayout(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.WRAP_CONTENT
        )
        btn_dialog_date.setOnClickListener {
            btn_dialog_date.setBackgroundResource(R.drawable.back_orange_btn)
            btn_dialog_time.setBackgroundColor(Color.TRANSPARENT)
            cal_dialog.visibility = View.VISIBLE
            tp_dialog.visibility = View.GONE
        }
        btn_dialog_time.setOnClickListener {
            btn_dialog_time.setBackgroundResource(R.drawable.back_orange_btn)
            btn_dialog_date.setBackgroundColor(Color.TRANSPARENT)
            cal_dialog.visibility = View.GONE
            tp_dialog.visibility = View.VISIBLE
        }
        ib_dialog_close.setOnClickListener { this.dismiss() }
        btn_dialog_ok.setOnClickListener {
            // TODO: 시간 설정 완료 리스너 실행
            listener()
            this.dismiss()
        }
        hideTimeHeaderLayout()
    }

    private fun hideTimeHeaderLayout() {
        val id: Int = Resources.getSystem().getIdentifier("time_header", "id", "android")
        val timeLayout = tp_dialog.findViewById<View>(id)
        if (timeLayout != null) {
            timeLayout.visibility = View.GONE
        }
    }
}