/*
 Copyright (c) 2020 Mustafa Ozhan. All rights reserved.
 */

package com.github.mustafaozhan.basemob.view.dialog

import android.content.Context
import androidx.fragment.app.DialogFragment
import dagger.android.support.AndroidSupportInjection

abstract class BaseDialogFragment : DialogFragment() {
    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }
}
