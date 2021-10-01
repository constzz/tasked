package com.gmail.konstantin.bezzemelnyi.common.extensions

import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment


fun Fragment.hideSoftKeyboard() {
    val imm = activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    val focusedView = activity?.currentFocus
    imm.hideSoftInputFromWindow(
        focusedView?.windowToken,
        InputMethodManager.HIDE_NOT_ALWAYS
    )
}

fun Context.hideKeyboard(focusedView: View) {
    val imm = this.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.hideSoftInputFromWindow(
        focusedView.windowToken,
        InputMethodManager.HIDE_NOT_ALWAYS
    )
}