package de.moyapro.shopping.itemlist

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import de.moyapro.shopping.event.ItemAddedEvent
import de.moyapro.shopping.event.ItemUpdatedEvent
import de.moyapro.shopping.model.Item
import org.greenrobot.eventbus.EventBus

@Composable
fun EditItemComponent(item: Item, update: (Item) -> Unit) {
    var isEdited: Boolean by remember {
        mutableStateOf(false)
    }
    when (isEdited) {
        false -> displayView(item) { isEdited = !isEdited }
        true -> editView(
            item,
            { isEdited = !isEdited },
            { editedItem -> postItemEditedEvent(editedItem) })
    }

}

@Composable
fun editView(item: Item, switchMode: () -> Unit, save: (Item) -> Unit) {
    var editName by remember { mutableStateOf(item.itemName) }
    Column {
        TextField(
            value = editName,
            onValueChange = { newValue -> editName = newValue },
            modifier = Modifier
                .fillMaxWidth()
                .clickable(onClick = switchMode),
        )
        Row {
            Button(onClick = {
                save(item.copy(itemName = editName, added = true))
                switchMode()
            }) {
                Text(text = "✓")
            }
            Button(onClick = switchMode) {
                Text(text = "✗")
            }
        }
    }
}

@Composable
private fun displayView(
    item: Item,
    switchMode: () -> Unit
) {
    Text(
        text = item.itemName,
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = switchMode)
    )
}


private fun postItemEditedEvent(updatedItem: Item) {
    EventBus.getDefault().post(
        ItemUpdatedEvent(updatedItem)
    )
}