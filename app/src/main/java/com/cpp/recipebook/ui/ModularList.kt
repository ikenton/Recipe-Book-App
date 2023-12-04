package com.cpp.recipebook.ui

import androidx.compose.runtime.Composable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ListItem
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun ModularList(items: MutableList<String>, onItemAdded: (String) -> Unit) {
    var text by remember { mutableStateOf("") }
    Column {
        items.forEach { item ->
            ListItem(headlineContent = { Text(item) })
        }
        OutlinedTextField(value = text, onValueChange = { newItem ->
            text = newItem
        },
            keyboardActions = KeyboardActions(
                onDone = {
                    if (text.isNotEmpty()) {
                        onItemAdded(text)
                        text = ""
                    }
                }
            ),
            keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done),
            label = { Text("Add Item") },
            modifier = Modifier.fillMaxWidth()
        )
    }
}


@Preview
@Composable
fun PreviewModularList() {
    Column {
        val items = mutableListOf("Ground chuck beef", "Lettuce", "Onions")
        ModularList(items = items, onItemAdded = { items.add(it) })
    }
}