package de.moyapro.shopping.itemlist

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import de.moyapro.shopping.event.ItemCheckedEvent
import de.moyapro.shopping.model.Item
import org.greenrobot.eventbus.EventBus

@Composable
fun ShoppingItemComponent(item: Item, update: (Item) -> Unit) {
    val backgroundColor by animateColorAsState(
        targetValue = if (item.checked) Color.Green else Color.Transparent
    )
    Text(
        text = item.itemName,
        modifier = Modifier
            .fillMaxWidth()
            .background(color = backgroundColor)
            .clickable(onClick = {
                val updatedItem = item.copy(checked = !item.checked)
                EventBus
                    .getDefault()
                    .post(ItemCheckedEvent(updatedItem))
                update(updatedItem)
            })
    )
}