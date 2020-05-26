/*
 Copyright (c) 2020 Mustafa Ozhan. All rights reserved.
 */
package com.github.mustafaozhan.basemob.util

import android.app.Activity
import android.app.AlertDialog
import com.github.mustafaozhan.basemob.R

@Suppress("LongParameterList")
fun showDialog(
    activity: Activity,
    title: String,
    description: String,
    positiveButton: String,
    cancelable: Boolean = true,
    function: (() -> Unit)? = null
) {
    if (!activity.isFinishing) {
        val dialog = AlertDialog
            .Builder(activity, R.style.AlertDialogCustom)
            .setIcon(R.mipmap.ic_launcher)
            .setTitle(title)
            .setMessage(description)
            .setPositiveButton(positiveButton) { _, _ -> function?.invoke() }

        if (cancelable) {
            dialog
                .setCancelable(cancelable)
                .setNegativeButton(activity.getString(android.R.string.cancel), null)
        }

        dialog.show()
    }
}

@Suppress("LongParameterList")
fun showDialog(
    activity: Activity,
    title: Int,
    description: Int,
    positiveButton: Int,
    cancelable: Boolean = true,
    function: (() -> Unit)? = null
) {
    if (!activity.isFinishing) {
        val dialog = AlertDialog
            .Builder(activity, R.style.AlertDialogCustom)
            .setIcon(R.mipmap.ic_launcher)
            .setTitle(activity.getString(title))
            .setMessage(activity.getString(description))
            .setPositiveButton(activity.getText(positiveButton)) { _, _ -> function?.invoke() }

        if (cancelable) {
            dialog
                .setCancelable(cancelable)
                .setNegativeButton(activity.getString(android.R.string.cancel), null)
        }
        dialog.show()
    }
}
