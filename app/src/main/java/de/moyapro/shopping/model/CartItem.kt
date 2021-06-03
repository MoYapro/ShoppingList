package de.moyapro.shopping.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "cartitem")
data class CartItem(
    @PrimaryKey(autoGenerate = true)
    val cartItemId: Long,
    val itemId: Long,
    val amount: Int,
    val checked: Boolean
) {
    constructor(itemId: Long) : this(0, itemId, 1, false)
}