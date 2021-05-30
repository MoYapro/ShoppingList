package de.moyapro.shopping.itemlist

import de.moyapro.shopping.event.ItemAddedEvent
import de.moyapro.shopping.event.ReloadEvent
import de.moyapro.shopping.event.RemoveCheckedEvent
import de.moyapro.shopping.model.Item
import de.moyapro.shopping.repository.CartItemRepository
import de.moyapro.shopping.repository.ItemRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import org.greenrobot.eventbus.Subscribe

class ItemListController(
    private val cartItemRepository: CartItemRepository,
    private val viewModel: AppViewModel
) {

    @Subscribe
    fun addItem(itemAddedEvent: ItemAddedEvent) {
        viewModel.addItem(itemAddedEvent.newItem)
    }

    @Subscribe
    fun clearChecked(removeCheckedEvent: RemoveCheckedEvent) {
        viewModel.removeCheckedItems()
    }

    @Subscribe
    fun reload(removeCheckedEvent: ReloadEvent) {
        runBlocking {
            launch {
                lateinit var loadedItems: List<Item>
                withContext(Dispatchers.IO) {
                    loadedItems = cartItemRepository.getAll().map { it.item }
                }
                viewModel.setItems(loadedItems.filter { it.added })
            }
        }
    }

}