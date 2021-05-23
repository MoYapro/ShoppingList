package de.moyapro.shopping.model

import androidx.annotation.WorkerThread
import androidx.room.*
import java.util.*

@Dao
interface ItemRepository {
    @WorkerThread
    @Query("SELECT * FROM item")
    suspend fun getAll(): List<Item>

    @Query("SELECT * FROM Item WHERE id IN (:idList)")
    suspend fun loadAllByIds(vararg idList: Long): List<Item>

    @Query("SELECT * FROM Item WHERE name LIKE :name")
    suspend fun findName(name: String): Item

    @Insert
    suspend fun insertAll(vararg Items: Item)

    @Delete
    suspend fun delete(Item: Item)
}