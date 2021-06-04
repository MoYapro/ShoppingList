package de.moyapro.shopping.dao

import androidx.room.*
import de.moyapro.shopping.model.CartItem
import de.moyapro.shopping.model.CartItemRelation
import de.moyapro.shopping.model.Item

@Dao
interface CartItemDao {

    @Transaction
    @Query("SELECT * FROM cartitem")
    suspend fun getAll(): List<CartItemRelation>

    @Transaction
    @Query("SELECT * FROM cartitem WHERE checked")
    suspend fun getChecked(): List<CartItemRelation>

    @Delete
    suspend fun removeAll(cartItemRelationList: List<CartItem>)

    @Update
    suspend fun updateAll(vararg items: CartItem)

    @Update
    suspend fun updateAll(vararg items: Item)

}