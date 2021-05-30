package de.moyapro.shopping.itemlist

import de.moyapro.shopping.event.ItemUpdatedEvent
import de.moyapro.shopping.repository.CartItemRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import org.greenrobot.eventbus.Subscribe

class UpdateItemController(
    private val cartItemRepository: CartItemRepository,
    private val viewModel: AppViewModel
) {

    @Subscribe
    fun updateItem(itemAddedEvent: ItemUpdatedEvent) {
        val item = itemAddedEvent.updatedItem
        viewModel.updateItem(item)
        runBlocking {
            launch {
                withContext(Dispatchers.IO) {
                    cartItemRepository.updateAll(item.cartItem)
                    cartItemRepository.updateAll(item.item)
                }
            }
        }
    }
}