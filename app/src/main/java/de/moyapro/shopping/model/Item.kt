package de.moyapro.shopping.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "item")
data class Item constructor (
    var itemName: String,
    var checked: Boolean,
    var added: Boolean
) {
    @PrimaryKey(autoGenerate = true)
    var itemId: Long = 0
}
