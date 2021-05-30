package de.moyapro.shopping.repository

import androidx.room.Dao
import androidx.room.Query
import de.moyapro.shopping.model.CartItemRelation

@Dao
interface CartItemRepository {

    @Query("SELECT * FROM cartitem")
    fun getAll(): List<CartItemRelation>

}