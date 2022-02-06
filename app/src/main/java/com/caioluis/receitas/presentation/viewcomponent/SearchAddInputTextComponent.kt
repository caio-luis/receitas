package com.caioluis.receitas.presentation.viewcomponent

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import androidx.appcompat.widget.AppCompatImageButton
import com.caioluis.receitas.R
import com.google.android.material.textfield.TextInputLayout
import io.reactivex.subjects.PublishSubject

class SearchAddInputTextComponent(
    context: Context,
    attrs: AttributeSet? = null
) : LinearLayout(context, attrs) {

    val addButtonOutput = PublishSubject.create<String>()
    val searchButtonOutput = PublishSubject.create<Unit>()

    private val searchButton: AppCompatImageButton by lazy { findViewById(R.id.search_button) }
    private val searchLayout: TextInputLayout by lazy { findViewById(R.id.search_add_layout) }

    init {
        LayoutInflater.from(context)
            .inflate(R.layout.component_search_add_text_input, this, true)
    }

    override fun onFinishInflate() {
        super.onFinishInflate()
        setListeners()
    }

    private fun setListeners() {
        searchLayout.apply {
            setEndIconOnClickListener {
                editText?.text?.let {
                    if (it.isNotBlank()) {
                        addButtonOutput.onNext(it.toString())
                        it.clear()
                    }
                }
            }
        }

        searchButton.setOnClickListener {
            searchButtonOutput.onNext(Unit)
        }
    }
}
