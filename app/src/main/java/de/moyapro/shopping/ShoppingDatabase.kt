package de.moyapro.shopping

import androidx.room.Database
import androidx.room.RoomDatabase
import de.moyapro.shopping.model.Item
import de.moyapro.shopping.repository.ItemRepository

@Database(
    entities = [
        Item::class
    ], version = 2
)
abstract class ShoppingDatabase : RoomDatabase() {
    abstract fun itemRepository(): ItemRepository
}