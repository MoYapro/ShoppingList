package de.moyapro.shopping.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "item")
data class Item(
    @PrimaryKey
    val id: Long,
    val name: String,
    val checked: Boolean = false
) {
    constructor(name: String, checked: Boolean = false) : this(Random().nextLong(), name, checked)
}
