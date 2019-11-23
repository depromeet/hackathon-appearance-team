package com.depromeet.hackthon7th.ext

import android.widget.EditText
import com.depromeet.hackthon7th.R
import com.google.android.material.textfield.TextInputLayout


fun TextInputLayout.showMessage(strResId: Int) {
    this.error = context.getString(strResId)
}

fun TextInputLayout.hideMessage() {
    this.error = null
}

fun EditText.checkLengthAndShowMessage(layout: TextInputLayout, text: String): Boolean {
    when {
        text.isEmpty() -> layout.showMessage(R.string.task_detail_empty)
        text.length == 20 -> {
            layout.showMessage(R.string.task_detail_max)
        }
        text.length > 20 -> {
            this.setText(text.substring(0, 20))
            this.setSelection(20)
        }
        else -> {
            layout.hideMessage()
            return true
        }
    }

    return false
}