package com.depromeet.hackthon7th.ext

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.LinearLayout
import com.depromeet.hackthon7th.R


class BottomSheetLayout @JvmOverloads
constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : LinearLayout(
    context, attrs, defStyleAttr
) {
    private val inAnimation = AnimationUtils.loadAnimation(
        context,
        R.anim.slide_up
    )
    private val outAnimation = AnimationUtils.loadAnimation(
        context,
        R.anim.slide_down
    )

    override fun setVisibility(visibility: Int) {
        when (visibility) {
            View.VISIBLE -> startAnimation(inAnimation)
            View.INVISIBLE, View.GONE -> startAnimation(outAnimation)
        }

        super.setVisibility(visibility)
    }
}
