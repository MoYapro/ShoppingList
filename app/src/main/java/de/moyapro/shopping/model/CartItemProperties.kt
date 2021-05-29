package de.moyapro.shopping.model

import androidx.room.PrimaryKey

data class CartItemProperties(
    @PrimaryKey
    val cartItemId: Long,
    val amount: Int,
    val itemId: Long,
    val checked: Boolean
)