package de.moyapro.shopping.additem

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import de.moyapro.shopping.model.Item

@Dao
interface ItemRepository {
    @Query("SELECT * FROM Item WHERE id IN (:idList)")
    fun loadAllByIds(vararg idList: Long): List<Item>
    @Insert
    fun insertAll(vararg Items: Item)
}