package de.moyapro.shopping.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cartitem")
data class CartItem(
    @PrimaryKey
    val cartItemId: Long,
    val itemId: Long,
    val amount: Int,
    val checked: Boolean
)