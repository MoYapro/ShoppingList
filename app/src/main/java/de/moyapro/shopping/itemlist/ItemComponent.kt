package de.moyapro.shopping.itemlist

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.LiveData
import de.moyapro.shopping.AppState
import de.moyapro.shopping.event.ItemCheckedEvent
import de.moyapro.shopping.model.Item
import org.greenrobot.eventbus.EventBus

@Composable
fun ItemComponent(item: Item, state: AppState, update: (Item) -> Unit) {
    val backgroundColor by animateColorAsState(
        targetValue = if (item.checked) Color.Green else Color.Transparent
    )
    val displayText = when(state) {
        AppState.PLANNING -> item.toString()
        AppState.SHOPPING -> item.itemName
    }
    Text(
        text = displayText,
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