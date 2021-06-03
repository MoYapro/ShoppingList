package de.moyapro.shopping.itemlist

import de.moyapro.shopping.event.ItemUpdatedEvent
import de.moyapro.shopping.dao.CartItemDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import org.greenrobot.eventbus.Subscribe

class UpdateItemController(
    private val cartItemDao: CartItemDao,
    private val viewModel: AppViewModel
) {

    @Subscribe
    fun updateItem(itemAddedEvent: ItemUpdatedEvent) {
        val item = itemAddedEvent.updatedItem
        viewModel.updateItem(item)
        runBlocking {
            launch {
                withContext(Dispatchers.IO) {
                    cartItemDao.updateAll(item.cartItem)
                    cartItemDao.updateAll(item.item)
                }
            }
        }
    }
}