package de.moyapro.shopping.event

import de.moyapro.shopping.model.Item

data class ItemUpdatedEvent(
    val updatedItem: Item
)
