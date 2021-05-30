package de.moyapro.shopping.event

import de.moyapro.shopping.model.CartItemRelation
import de.moyapro.shopping.model.Item

data class ItemAddedEvent(
    val newItem: Item
)
