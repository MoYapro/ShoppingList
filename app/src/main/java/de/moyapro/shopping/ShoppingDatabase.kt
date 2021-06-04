package de.moyapro.shopping

import androidx.room.Database
import androidx.room.RoomDatabase
import de.moyapro.shopping.model.CartItem
import de.moyapro.shopping.model.Item
import de.moyapro.shopping.dao.CartItemDao
import de.moyapro.shopping.dao.ItemDao

@Database(
    entities = [
        Item::class,
        CartItem::class,
    ], version = 4
)
abstract class ShoppingDatabase : RoomDatabase() {
    abstract fun itemRepository(): ItemDao
    abstract fun cartItemRepository(): CartItemDao
}