package de.moyapro.shopping.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cartitem")
data class CartItem(
    val itemId: Long,
    val amount: Int,
    val checked: Boolean
) {
    @PrimaryKey(autoGenerate = true)
    var cartItemId: Long = 0

    constructor(itemId: Long) : this(itemId, 1, false)
}