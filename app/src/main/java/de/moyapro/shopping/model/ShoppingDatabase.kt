package de.moyapro.shopping.model

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Item::class], version = 1)
abstract class ShoppingDatabase : RoomDatabase() {
    abstract fun itemRepository(): ItemRepository
}