package de.moyapro.shopping.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "item")
data class Item constructor(
    @PrimaryKey
    val itemId: Long,
    val itemName: String,
    val checked: Boolean,
    val added: Boolean
) {
    constructor(name: String, checked: Boolean = false, added: Boolean = false) : this(Random().nextLong(), name, checked, added)
}
