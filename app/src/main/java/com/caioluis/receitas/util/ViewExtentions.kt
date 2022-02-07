package com.caioluis.receitas.util

import android.content.Context
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible

fun View.show() {
    if (this.isVisible.not())
        this.visibility = View.VISIBLE
}

fun View.hide() {
    if (this.isVisible)
        this.visibility = View.GONE
}

fun Context.showToast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}