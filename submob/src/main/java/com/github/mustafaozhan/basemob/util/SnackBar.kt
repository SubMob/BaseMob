/*
 Copyright (c) 2020 Mustafa Ozhan. All rights reserved.
 */
package com.github.mustafaozhan.basemob.util

import android.graphics.Typeface
import android.view.View
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.github.mustafaozhan.basemob.R
import com.google.android.material.snackbar.Snackbar

@Suppress("LongParameterList")
fun showSnack(
    view: View,
    text: Int? = null,
    actionText: Int? = null,
    icon: Int? = null,
    isIndefinite: Boolean = false,
    action: (() -> Unit)? = null
) = Snackbar.make(
    view,
    text?.let { view.context?.getString(it) } ?: "",
    if (isIndefinite) Snackbar.LENGTH_INDEFINITE else Snackbar.LENGTH_LONG
).apply {
    setAction(actionText?.let { context.getString(it) } ?: "") { action?.invoke() }
    this.view.apply {
        setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimarySemiDark))
        findViewById<TextView>(R.id.snackbar_text)
            ?.setCompoundDrawablesWithIntrinsicBounds(icon ?: R.mipmap.ic_launcher, 0, 0, 0)
        findViewById<TextView>(R.id.snackbar_action)?.apply {
            setTypeface(null, Typeface.BOLD)
            setTextColor(ContextCompat.getColor(context, R.color.cyan_700))
        }
    }
}.show()

@Suppress("LongParameterList")
fun showSnack(
    view: View,
    text: String = "",
    actionText: String = "",
    icon: Int? = null,
    isIndefinite: Boolean = false,
    action: (() -> Unit)? = null
) = Snackbar.make(
    view,
    text,
    if (isIndefinite) Snackbar.LENGTH_INDEFINITE else Snackbar.LENGTH_LONG
).apply {
    setAction(actionText) { action?.invoke() }
    this.view.apply {
        setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimarySemiDark))
        findViewById<TextView>(R.id.snackbar_text)
            .setCompoundDrawablesWithIntrinsicBounds(icon ?: R.mipmap.ic_launcher, 0, 0, 0)
        findViewById<TextView>(R.id.snackbar_action).apply {
            setTypeface(null, Typeface.BOLD)
            setTextColor(ContextCompat.getColor(context, R.color.cyan_700))
        }
    }
}.show()
