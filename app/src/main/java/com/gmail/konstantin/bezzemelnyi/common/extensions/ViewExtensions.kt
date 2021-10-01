package com.gmail.konstantin.bezzemelnyi.common.extensions

import android.view.View
import androidx.core.view.WindowInsetsCompat

fun View.keyboardIsVisible(): Boolean {
    return WindowInsetsCompat
        .toWindowInsetsCompat(rootWindowInsets)
        .isVisible(WindowInsetsCompat.Type.ime())
}
