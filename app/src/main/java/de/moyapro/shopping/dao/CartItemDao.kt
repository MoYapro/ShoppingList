package de.moyapro.shopping.dao

import androidx.room.*
import de.moyapro.shopping.model.CartItem
import de.moyapro.shopping.model.CartItemRelation
import de.moyapro.shopping.model.Item

@Dao
interface CartItemDao {

    @Transaction
    @Query("SELECT * FROM cartitem")
    fun getAll(): List<CartItemRelation>

    @Transaction
    @Query("SELECT * FROM cartitem WHERE checked")
    fun getChecked(): List<CartItemRelation>

    @Delete
    fun removeAll(cartItemRelationList: List<CartItem>)

    @Update
    fun updateAll(vararg items: CartItem)

    @Update
    fun updateAll(vararg items: Item)

}