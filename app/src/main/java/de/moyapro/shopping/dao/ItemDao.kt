package de.moyapro.shopping.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import de.moyapro.shopping.model.Item

@Dao
interface ItemDao {
    @Query("SELECT * FROM item")
    suspend fun getAll(): List<Item>

    @Query("SELECT * FROM Item WHERE itemId IN (:idList)")
    suspend fun loadAllByIds(vararg idList: Long): List<Item>

    @Insert
    suspend fun insertAll(vararg item: Item): List<Long>

    @Insert
    suspend fun insertAll(items: List<Item>): List<Long>

    @Update
    suspend fun updateAll(items: List<Item>)

    @Update
    suspend fun updateAll(vararg item: Item)

}