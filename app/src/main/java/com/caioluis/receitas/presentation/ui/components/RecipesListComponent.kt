package com.caioluis.receitas.presentation.ui.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.caioluis.receitas.presentation.model.RecipeViewModel

@ExperimentalMaterialApi
@Composable
fun RecipesList(recipes: List<RecipeViewModel>, itemClick: (RecipeViewModel) -> Unit) {
    LazyColumn(
        modifier = Modifier.fillMaxWidth(),
        contentPadding = PaddingValues(5.dp),
    ) {
        items(recipes) { recipe ->
            RecipeListItem(recipe = recipe, onClickAction = itemClick)
        }
    }
}
