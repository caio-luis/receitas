package com.caioluis.receitas.presentation.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.caioluis.receitas.presentation.model.RecipeViewModel

@ExperimentalMaterialApi
@Composable
fun RecipeListItem(recipe: RecipeViewModel, onClickAction: (RecipeViewModel) -> Unit) {
    Card(
        modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth()
            .wrapContentHeight(),
        shape = MaterialTheme.shapes.medium,
        elevation = 5.dp,
        backgroundColor = MaterialTheme.colors.surface,
        onClick = { onClickAction(recipe) }
    ) {
        Row(
            modifier = Modifier.padding(10.dp),
            horizontalArrangement = Arrangement.Center,
        ) {
            Text(text = recipe.recipeName)
        }
    }
}
