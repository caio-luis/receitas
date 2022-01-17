package com.caioluis.receitas.presentation.ui.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.caioluis.receitas.presentation.ui.theme.ReceitasTheme

@Composable
fun BasicAppBar(title: String) {
    TopAppBar(
        backgroundColor = MaterialTheme.colors.primary,
        title = { Text(text = title) },
        navigationIcon = {
            Icon(
                modifier = Modifier.padding(horizontal = 8.dp),
                imageVector = Icons.Filled.ArrowBack,
                contentDescription = ""
            )
        },
    )
}

@Composable
@ExperimentalMaterialApi
fun BasicActivityLayout(
    appBar: @Composable () -> Unit,
    activityContent: @Composable () -> Unit
) {
    ReceitasTheme(darkTheme = false) {
        Surface {
            Scaffold(
                topBar = { appBar() },
                content = { activityContent() }
            )
        }
    }
}