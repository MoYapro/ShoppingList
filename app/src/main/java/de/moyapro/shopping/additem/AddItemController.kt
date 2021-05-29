package de.moyapro.shopping.additem

import de.moyapro.shopping.event.AddItemEvent
import de.moyapro.shopping.repository.ItemRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import org.greenrobot.eventbus.Subscribe

class AddItemController(
    private val itemRepository: ItemRepository
) {

    @Subscribe
    fun addItem(addItemEvent: AddItemEvent) {
        val item = addItemEvent.newItem
        runBlocking {
            launch {
                withContext(Dispatchers.IO) {
                    itemRepository.insertAll(item)
                }
            }
        }
    }
}