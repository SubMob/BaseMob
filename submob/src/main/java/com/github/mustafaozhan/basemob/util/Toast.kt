/*
 Copyright (c) 2020 Mustafa Ozhan. All rights reserved.
 */
package com.github.mustafaozhan.basemob.util

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.github.mustafaozhan.basemob.R

object Toast {
    private const val iconPadding = 10

    @Suppress("ObjectPropertyName")
    private var _toast: Toast? = null

    fun show(
        context: Context,
        text: String,
        isLong: Boolean = true,
        tintColor: Int? = null,
        textColor: Int = Color.WHITE
    ) {
        _toast?.cancel()
        _toast = Toast.makeText(
            context,
            text,
            if (isLong) Toast.LENGTH_LONG else Toast.LENGTH_SHORT
        )
        tintColor ?: R.color.colorPrimaryBlack.let {
            _toast?.view?.apply {
                setBackgroundResource(android.R.drawable.toast_frame)
                (background as? GradientDrawable)?.setColor(ContextCompat.getColor(context, it))
                findViewById<TextView>(android.R.id.message)?.apply {
                    setTextColor(textColor)
                    setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_info, 0, 0, 0)
                    compoundDrawablePadding = iconPadding
                }
            }
        }
        _toast?.show()
    }

    fun show(
        context: Context,
        text: Int,
        isLong: Boolean = true,
        tintColor: Int? = null,
        textColor: Int = Color.WHITE
    ) {
        _toast?.cancel()
        _toast = Toast.makeText(
            context,
            context.getString(text),
            if (isLong) Toast.LENGTH_LONG else Toast.LENGTH_SHORT
        )
        tintColor ?: R.color.colorPrimaryBlack.let {
            _toast?.view?.apply {
                setBackgroundResource(android.R.drawable.toast_frame)
                (background as? GradientDrawable)?.setColor(ContextCompat.getColor(context, it))
                findViewById<TextView>(android.R.id.message)?.apply {
                    setTextColor(textColor)
                    setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_info, 0, 0, 0)
                    compoundDrawablePadding = iconPadding
                }
            }
        }
        _toast?.show()
    }
}
