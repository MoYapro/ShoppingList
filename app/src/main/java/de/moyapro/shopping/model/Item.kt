package de.moyapro.shopping.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "item")
data class Item constructor (
    val itemName: String,
    val checked: Boolean,
    val added: Boolean
) {
    @PrimaryKey(autoGenerate = true)
    var itemId: Long = 0
}
