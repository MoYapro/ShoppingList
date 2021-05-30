package de.moyapro.shopping.itemlist

import de.moyapro.shopping.event.ItemAddedEvent
import de.moyapro.shopping.event.ReloadEvent
import de.moyapro.shopping.event.RemoveCheckedEvent
import de.moyapro.shopping.model.CartItem
import de.moyapro.shopping.model.CartItemRelation
import de.moyapro.shopping.repository.CartItemRepository
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
        val addedItem = itemAddedEvent.newItem
        viewModel.addItem(
            CartItemRelation(
                CartItem(addedItem.itemId),
                addedItem
            )
        )
    }

    @Subscribe
    fun clearChecked(removeCheckedEvent: RemoveCheckedEvent) {
        viewModel.removeCheckedItems()
    }

    @Subscribe
    fun reload(removeCheckedEvent: ReloadEvent) {
        runBlocking {
            launch {
                lateinit var loadedItems: List<CartItemRelation>
                withContext(Dispatchers.IO) {
                    loadedItems = cartItemRepository.getAll()
                }
                viewModel.setItems(loadedItems)
            }
        }
    }

}