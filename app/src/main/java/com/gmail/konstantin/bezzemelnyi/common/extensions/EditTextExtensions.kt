package com.gmail.konstantin.bezzemelnyi.common.extensions

import android.text.InputType
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import android.widget.TextView

fun EditText.setOnEditorActionDone(function: () -> Unit) {
    this.setOnEditorActionListener(
        TextView.OnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                this.clearFocus()
                context.hideKeyboard(this)
                function()
                return@OnEditorActionListener true
            }
            false
        },
    )
}


/**
 *Workaround to show action done on soft keyboard as well as wrap words on multiple lines
 **/
fun EditText.setMultilineActionInputType() {
    this.imeOptions = EditorInfo.IME_ACTION_DONE
    this.setRawInputType(InputType.TYPE_CLASS_TEXT)
}