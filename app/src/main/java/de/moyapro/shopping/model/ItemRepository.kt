package de.moyapro.shopping.model

import androidx.room.*
import java.util.*

@Dao
interface ItemRepository {
    @Query("SELECT * FROM item")
    fun getAll(): List<Item>

    @Query("SELECT * FROM Item WHERE id IN (:idList)")
    fun loadAllByIds(vararg idList: Long): List<Item>

    @Query("SELECT * FROM Item WHERE name LIKE :name")
    fun findName(name: String): Item

    @Insert
    fun insertAll(vararg Items: Item)

    @Delete
    fun delete(Item: Item)
}