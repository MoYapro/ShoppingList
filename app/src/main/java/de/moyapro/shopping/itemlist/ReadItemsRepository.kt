package de.moyapro.shopping.itemlist

import androidx.room.*
import de.moyapro.shopping.model.Item

@Dao
interface ReadItemsRepository {
    @Query("SELECT * FROM item")
    fun getAll(): List<Item>
}