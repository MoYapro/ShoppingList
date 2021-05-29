package de.moyapro.shopping.repository

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import de.moyapro.shopping.model.Item

@Dao
interface ItemRepository {
    @Query("SELECT * FROM item")
    fun getAll(): List<Item>

    @Query("SELECT * FROM Item WHERE itemId IN (:idList)")
    fun loadAllByIds(vararg idList: Long): List<Item>

    @Insert
    fun insertAll(vararg Items: Item)
}