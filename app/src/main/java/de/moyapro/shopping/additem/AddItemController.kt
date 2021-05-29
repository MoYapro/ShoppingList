package de.moyapro.shopping.additem

import de.moyapro.shopping.event.AddItemEvent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import org.greenrobot.eventbus.Subscribe

class AddItemController(
    private val addItemRepository: AddItemRepository
) {

    @Subscribe
    fun addItem(addItemEvent: AddItemEvent) {
        val item = addItemEvent.newItem
        runBlocking {
            launch {
                withContext(Dispatchers.IO) {
                    addItemRepository.insertAll(item)
                }
            }
        }
    }
}