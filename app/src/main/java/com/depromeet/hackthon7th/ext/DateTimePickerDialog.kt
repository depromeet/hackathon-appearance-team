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
    private enum class TIMEZONE {
        AM, PM
    }

    private var timezone: TIMEZONE = TIMEZONE.AM

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
            layout_dialog_time.visibility = View.GONE
        }
        btn_dialog_time.setOnClickListener {
            btn_dialog_time.setBackgroundResource(R.drawable.back_orange_btn)
            btn_dialog_date.setBackgroundColor(Color.TRANSPARENT)
            cal_dialog.visibility = View.GONE
            layout_dialog_time.visibility = View.VISIBLE
        }
        ib_dialog_close.setOnClickListener { this.dismiss() }
        btn_dialog_ok.setOnClickListener {
            // TODO: 시간 설정 완료 리스너 실행
            listener()
            this.dismiss()
        }
        btn_dialog_am.setOnClickListener { switchTimeZone(true) }
        btn_dialog_pm.setOnClickListener { switchTimeZone(false) }
        hideTimeHeaderLayout()
    }

    private fun switchTimeZone(isAM: Boolean) {
        timezone = if (isAM) TIMEZONE.AM else TIMEZONE.PM
        btn_dialog_am.setBackgroundResource(
            if (isAM) R.drawable.back_gray_btn else R.drawable.back_darker_gray_btn
        )
        btn_dialog_pm.setBackgroundResource(
            if (isAM) R.drawable.back_darker_gray_btn else R.drawable.back_gray_btn
        )
    }

    private fun hideTimeHeaderLayout() {
        val id: Int = Resources.getSystem().getIdentifier("time_header", "id", "android")
        tp_dialog.findViewById<View>(id)?.let {
            it.visibility = View.GONE
        }
    }
}