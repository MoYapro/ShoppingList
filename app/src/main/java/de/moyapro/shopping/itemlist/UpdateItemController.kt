package de.moyapro.shopping.itemlist

import de.moyapro.shopping.event.ItemUpdatedEvent
import de.moyapro.shopping.repository.ItemRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import org.greenrobot.eventbus.Subscribe

class UpdateItemController(
    private val itemRepository: ItemRepository,
    private val viewModel: AppViewModel
) {

    @Subscribe
    fun updateItem(itemAddedEvent: ItemUpdatedEvent) {
        val item = itemAddedEvent.updatedItem
        viewModel.updateItem(item)
        runBlocking {
            launch {
                withContext(Dispatchers.IO) {
                    itemRepository.updateAll(item)
                }
            }
        }
    }
}