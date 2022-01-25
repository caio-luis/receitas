package com.caioluis.receitas.presentation.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun ChipGroup(ingredients: List<String>) {
    Column(modifier = Modifier.padding(8.dp)) {
        LazyRow {
            items(ingredients) { ingredient ->
                Chip(name = ingredient)
            }
        }
    }
}

@Composable
fun Chip(name: String) {
    Surface(
        modifier = Modifier.padding(4.dp),
        elevation = 8.dp,
        shape = MaterialTheme.shapes.small,
        color = Color.Black
    ) {
        val visible = remember { mutableStateOf(true) }

        if (visible.value) {
            Row {
                Text(
                    text = name,
                    style = MaterialTheme.typography.body2,
                    color = Color.White,
                    modifier = Modifier
                        .padding(8.dp)
                        .align(Alignment.CenterVertically),
                    textAlign = TextAlign.Center
                )

                IconButton(
                    onClick = { visible.value = false },
                    modifier = Modifier.align(Alignment.CenterVertically)
                ) {
                    Icon(
                        imageVector = Icons.Filled.Close,
                        contentDescription = "",
                        tint = Color.White
                    )
                }
            }
        }
    }
}