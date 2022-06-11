package com.caioluis.receitas.presentation.viewcomponent

import android.content.Context
import android.graphics.Typeface
import android.util.AttributeSet
import android.view.Gravity
import android.view.LayoutInflater
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.view.marginBottom
import androidx.core.view.marginTop
import androidx.recyclerview.widget.RecyclerView
import com.caioluis.receitas.R
import com.caioluis.receitas.presentation.model.DetailType
import com.caioluis.receitas.presentation.model.RecipeDetailViewModel

const val TITLE_GENERAL_PADDING_DP = 20

class RecipeDetailComponent(context: Context, attrs: AttributeSet? = null) :
    AppCompatTextView(context, attrs) {

    init {
        LayoutInflater.from(context).inflate(R.layout.recipe_detail_item, null, true)
    }

    fun render(recipe: RecipeDetailViewModel) {
        text = recipe.text

        when (recipe.type) {
            DetailType.TITLE -> {
                gravity = Gravity.CENTER
                textAlignment = TEXT_ALIGNMENT_CENTER
                typeface = Typeface.create(Typeface.DEFAULT, Typeface.BOLD)
                textSize = 26F
                setPadding(
                    TITLE_GENERAL_PADDING_DP,
                    TITLE_GENERAL_PADDING_DP,
                    TITLE_GENERAL_PADDING_DP,
                    TITLE_GENERAL_PADDING_DP
                )
            }

            DetailType.SECTION_NAME -> {
                typeface = Typeface.create(Typeface.DEFAULT, Typeface.BOLD)
                textSize = 20F
                setPadding(0, 16, 0, 8)
            }

            DetailType.DESCRIPTION -> {
                typeface = Typeface.create(Typeface.DEFAULT, Typeface.NORMAL)
                textSize = 16F
            }
        }
    }
}
