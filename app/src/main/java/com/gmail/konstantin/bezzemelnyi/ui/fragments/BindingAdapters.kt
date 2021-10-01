package com.gmail.konstantin.bezzemelnyi.ui.fragments

import android.view.View
import androidx.databinding.BindingAdapter

object BindingAdapters {

    @BindingAdapter("android:emptyState")
    @JvmStatic
    fun emptyState(view: View, empty: Boolean) {
        view.visibility = when (empty) {
            true -> View.VISIBLE
            false -> View.GONE
        }
    }

}
