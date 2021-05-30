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
import de.moyapro.shopping.model.CartItemRelation
import org.greenrobot.eventbus.EventBus

@Composable
fun ShoppingItemComponent(cartItem: CartItemRelation, update: (CartItemRelation) -> Unit) {
    val backgroundColor by animateColorAsState(
        targetValue = if (cartItem.cartItem.checked) Color.Green else Color.Transparent
    )
    Text(
        text = cartItem.item.itemName,
        modifier = Modifier
            .fillMaxWidth()
            .background(color = backgroundColor)
            .clickable(onClick = {
                val updatedCartItem = cartItem.cartItem.copy(checked = !cartItem.cartItem.checked)
                val newItem = CartItemRelation(updatedCartItem, cartItem.item)
                EventBus
                    .getDefault()
                    .post(ItemCheckedEvent(newItem))
                update(newItem)
            })
    )
}