package com.depromeet.hackthon7th.ext

import android.widget.Toast
import com.depromeet.hackthon7th.RootApplication

object Toaster {

    @JvmStatic
    fun showShort(text: String = "") {
        Toast.makeText(RootApplication.getContext(), text, Toast.LENGTH_SHORT).show()
    }

    @JvmStatic
    fun showShort(resId: Int) {
        val context = RootApplication.getContext()
        Toast.makeText(context, context.getString(resId), Toast.LENGTH_SHORT).show()
    }
}