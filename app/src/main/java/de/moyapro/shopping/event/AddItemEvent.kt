package de.moyapro.shopping.event

import de.moyapro.shopping.model.Item

data class AddItemEvent(
    val newItem: Item
)
