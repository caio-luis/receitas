package com.caioluis.receitas.presentation.viewcomponent

import android.content.Context
import android.util.AttributeSet
import androidx.core.view.children
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import io.reactivex.subjects.PublishSubject

class IngredientsChipGroup(context: Context, attrs: AttributeSet? = null) :
    ChipGroup(context, attrs) {

    val closeIconClickOutput = PublishSubject.create<String>()

    fun renderChips(list: MutableList<String>) {
        val chips = this.children.map { (it as Chip).text }
        list.minus(chips).map { addChip(it.toString()) }
    }

    private fun addChip(title: String) {
        val chip = Chip(this.context).apply {
            this.text = title
            isCloseIconVisible = true

            setOnCloseIconClickListener {
                this@IngredientsChipGroup.removeView(this)
                closeIconClickOutput.onNext(title)
            }
        }
        this.addView(chip)
    }
}