package de.moyapro.shopping

import androidx.room.Database
import androidx.room.RoomDatabase
import de.moyapro.shopping.model.Item
import de.moyapro.shopping.itemlist.ReadItemsRepository

@Database(entities = [Item::class], version = 1)
abstract class ShoppingDatabase : RoomDatabase() {
    abstract fun itemRepository(): ReadItemsRepository
}