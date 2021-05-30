package de.moyapro.shopping.model

import androidx.room.Embedded
import androidx.room.Relation

data class CartItemRelation(
    @Embedded
    val cartItem: CartItem,
    @Relation(
        parentColumn = "itemId",
        entityColumn = "itemId"
    )
    val item: Item
)
