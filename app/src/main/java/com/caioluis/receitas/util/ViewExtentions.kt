package com.caioluis.receitas.util

import android.view.View
import androidx.core.view.isVisible

fun View.show() {
    if (this.isVisible.not())
        this.visibility = View.VISIBLE
}

fun View.hide() {
    if (this.isVisible)
        this.visibility = View.GONE
}