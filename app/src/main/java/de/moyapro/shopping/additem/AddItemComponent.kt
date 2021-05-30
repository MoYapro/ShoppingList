package de.moyapro.shopping.additem

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.unit.Dp
import de.moyapro.shopping.event.ItemAddedEvent
import de.moyapro.shopping.model.Item
import de.moyapro.shopping.repository.ItemRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import org.greenrobot.eventbus.EventBus
import java.util.*


@Composable
fun AddItemComponent(itemRepository: ItemRepository) {
    var textState by remember { mutableStateOf("") }
    val items =
        if ("" == textState) {
            emptyList()
        } else {
            var savedItemsList: List<Item> = emptyList()
            runBlocking {
                launch {
                    withContext(Dispatchers.IO) {
                        savedItemsList = itemRepository.getAll()
                    }
                }
            }
            savedItemsList.filter { item ->
                item.itemName.toLowerCase(Locale.getDefault()).contains(
                    textState.toLowerCase(
                        Locale.getDefault()
                    )
                )
            }
        }
    Column(
        modifier = Modifier
            .border(
                width = Dp(1F), brush = SolidColor(Color.Black), shape = RectangleShape
            )
            .fillMaxWidth()
    ) {
        items.forEach { suggestedItem ->
            ClickableText(text = AnnotatedString(suggestedItem.itemName), onClick = {
                postNewItemEvent(suggestedItem.itemName)
                textState = ""
            })
        }
    }
    Row {
        TextField(
            value = textState,
            onValueChange = { newText ->
                textState = newText
            }
        )
        Button(
            onClick = {
                postNewItemEvent(textState)
                textState = ""
            },
            modifier = Modifier.clip(shape = CircleShape)
        ) {
            Text("+")
        }
    }
}

private fun postNewItemEvent(suggestionText: String) {
    EventBus.getDefault().post(
        ItemAddedEvent(
            Item(name = suggestionText, checked = false, added = true)
        )
    )
}
