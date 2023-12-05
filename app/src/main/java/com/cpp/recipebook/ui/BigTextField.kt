package com.cpp.recipebook.ui

import androidx.compose.runtime.Composable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun BigTextField(label : String, items : MutableList<String>) {
    // for every item in the list, add it to the text field with a new line unless its the last item
    var text by remember { mutableStateOf("") }
    for (item in items) {
        text += item
        if (item != items.last()) {
            text += "\n"
        }
    }
    OutlinedTextField(
        value = text,
        onValueChange = { text = it },
        label = { Text(label) },
        modifier = Modifier.fillMaxWidth()
    )
}




@Preview
@Composable
fun PreviewModularList() {
    Column {
        val items = mutableListOf("Ground chuck beef", "Lettuce", "Onions")
        BigTextField(label = "Ingredients", items = items)
    }
}