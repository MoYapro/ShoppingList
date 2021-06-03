package de.moyapro.shopping

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import de.moyapro.shopping.model.CartItem
import de.moyapro.shopping.model.Item
import de.moyapro.shopping.dao.CartItemDao
import de.moyapro.shopping.dao.ItemDao
import junit.framework.TestCase
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ShoppingDatabaseTest: TestCase() {

    private lateinit var shoppingDb: ShoppingDatabase
    private lateinit var itemDao: ItemDao
    private lateinit var cartItemDao: CartItemDao

    @Before
    public override fun setUp() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        shoppingDb = Room.inMemoryDatabaseBuilder(context, ShoppingDatabase::class.java).build()
        itemDao = shoppingDb.itemRepository()
        cartItemDao = shoppingDb.cartItemRepository()
    }

    @After
    fun teardown() {
        shoppingDb.close()
    }

    @Test
    fun shouldReadAndWriteItem() {
        // Arrange
        val item = Item(name = "Item Name", checked = true, added = true)

        // Act
        itemDao.insertAll(item)
        val itemList = itemDao.getAll()

        // Assert
        assertTrue("itemList should has correct size", itemList.size == 1)
        assertTrue("itemId should be greater 0", 0 < itemList[0].itemId)
    }

    @Test
    fun shouldReadAndWriteCartItem() {
        // Arrange
        val item = Item(name = "Item Name", checked = true, added = true)


        // Act
        itemDao.insertAll(item)
        val itemResult = itemDao.getAll().first()
        val cartItem = CartItem(0, itemResult.itemId, 12, true)
        cartItemDao.updateAll(itemResult)
        cartItemDao.updateAll(cartItem)

        val cartItemResultList = cartItemDao.getAll()

        // Assert
        assertTrue("cartItemResultList should not be empty", cartItemResultList.isNotEmpty())
    }
}