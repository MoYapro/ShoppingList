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
import de.moyapro.shopping.event.ItemUpdatedEvent
import de.moyapro.shopping.model.CartItem
import de.moyapro.shopping.model.CartItemRelation
import org.greenrobot.eventbus.EventBus

@Composable
fun EditItemComponent(cartItem: CartItemRelation, update: (CartItemRelation) -> Unit) {
    var isEdited: Boolean by remember {
        mutableStateOf(false)
    }
    when (isEdited) {
        false -> displayView(cartItem) { isEdited = !isEdited }
        true -> editView(
            cartItem,
            { isEdited = !isEdited },
            { editedItem -> postItemEditedEvent(editedItem) })
    }

}

@Composable
fun editView(item: CartItemRelation, switchMode: () -> Unit, save: (CartItemRelation) -> Unit) {
    var editName by remember { mutableStateOf(item.item.itemName) }
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
                val updatedItem = item.item.copy(itemName = editName, added = true)
                save(CartItemRelation(CartItem(updatedItem.itemId), updatedItem))
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
    item: CartItemRelation,
    switchMode: () -> Unit
) {
    Text(
        text = item.item.itemName,
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = switchMode)
    )
}


private fun postItemEditedEvent(updatedItem: CartItemRelation) {
    EventBus.getDefault().post(
        ItemUpdatedEvent(updatedItem)
    )
}