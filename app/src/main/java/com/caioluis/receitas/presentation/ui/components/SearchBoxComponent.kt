package com.caioluis.receitas.presentation.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier

@Composable
fun SearchBoxComponent(
    hint: String,
    buttonAction: (String) -> Unit,
    onTextChange: (String) -> Unit = {},
    clearTextAfterSearch: Boolean = false
) {
    val inputText = remember { mutableStateOf("") }

    Row(modifier = Modifier.fillMaxWidth()) {

        OutlinedTextField(
            trailingIcon = {
                Icon(
                    imageVector = Icons.Filled.Add,
                    contentDescription = "",
                    Modifier.clickable {
                        buttonAction(inputText.value)
                        if (clearTextAfterSearch) inputText.value = ""
                    }
                )
            },
            modifier = Modifier.fillMaxWidth(),
            value = inputText.value,
            onValueChange = {
                inputText.value = it
                onTextChange(it)
            },
            label = { Text(text = hint) }
        )
    }
}