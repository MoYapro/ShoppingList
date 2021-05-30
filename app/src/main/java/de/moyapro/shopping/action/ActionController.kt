package de.moyapro.shopping.action

import de.moyapro.shopping.event.AppStateChangedEvent
import de.moyapro.shopping.event.ItemCheckedEvent
import de.moyapro.shopping.event.RemoveCheckedEvent
import de.moyapro.shopping.itemlist.AppViewModel
import de.moyapro.shopping.repository.ItemRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import org.greenrobot.eventbus.Subscribe

class ActionController(
    private val itemRepository: ItemRepository,
    private val viewModel: AppViewModel
) {

    @Subscribe
    fun updateAppState(appStateChangedEvent: AppStateChangedEvent) {
        viewModel.setState(appStateChangedEvent.newState)
    }

    @Subscribe
    fun clearChecked(removeCheckedEvent: RemoveCheckedEvent) {
        runBlocking {
            launch {
                withContext(Dispatchers.IO) {
                    val checkedItems = itemRepository.getChecked()
                    val items = checkedItems
                        .map { item ->
                            item.copy(added = false)
                        }
                    itemRepository.updateAll(
                        items
                    )
                }
            }
        }
    }

    @Subscribe
    fun checkItem(itemCheckedEventEvent: ItemCheckedEvent) {
        runBlocking {
            launch {
                withContext(Dispatchers.IO) {
                    itemRepository.updateAll(
                        itemCheckedEventEvent.item
                    )
                }
            }
        }
    }
}