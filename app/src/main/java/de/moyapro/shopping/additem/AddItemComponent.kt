package de.moyapro.shopping.ui.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.unit.Dp
import de.moyapro.shopping.event.AddItemEvent
import de.moyapro.shopping.itemlist.ItemListViewModel
import de.moyapro.shopping.model.Item
import org.greenrobot.eventbus.EventBus

@Composable
fun AddItemComponent(viewModel: ItemListViewModel) {
    val textState = remember { mutableStateOf("") }
    val items =
        if ("" == textState.value) {
            emptyList()
        } else {
            listOf(
                "Apfel",
                "Milch",
                "Butter",
                "Brot",
                "Bier",
                "Brei",
                "Banane",
            ).filter { it.contains(textState.value) }
        }
    Column(
        modifier = Modifier
            .border(
                width = Dp(1F), brush = SolidColor(Color.Black), shape = RectangleShape
            )
            .fillMaxWidth()
    ) {
        items.forEach { suggestionText ->
            ClickableText(text = AnnotatedString(suggestionText), onClick = {
                postNewItemEvent(suggestionText)
                textState.value = ""
            })
        }
    }
    Row {
        TextField(
            value = textState.value,
            onValueChange = { newText ->
                textState.value = newText
            }
        )
        Button(
            onClick = {
                postNewItemEvent(textState.value)
                textState.value = ""
            },
            modifier = Modifier.clip(shape = CircleShape)
        ) {
            Text("+")
        }
    }
}

private fun postNewItemEvent(suggestionText: String) {
    EventBus.getDefault().post(
        AddItemEvent(
            Item(name = suggestionText)
        )
    )
}
