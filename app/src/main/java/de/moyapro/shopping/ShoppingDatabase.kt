package de.moyapro.shopping

import androidx.room.Database
import androidx.room.RoomDatabase
import de.moyapro.shopping.additem.AddItemRepository
import de.moyapro.shopping.model.Item
import de.moyapro.shopping.itemlist.ReadItemsRepository

@Database(
    entities = [
        Item::class
    ], version = 2
)
abstract class ShoppingDatabase : RoomDatabase() {
    abstract fun readItemRepository(): ReadItemsRepository
    abstract fun addItemRepository(): AddItemRepository
}