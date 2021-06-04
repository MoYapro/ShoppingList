package de.moyapro.shopping.additem

import de.moyapro.shopping.event.ItemAddedEvent
import de.moyapro.shopping.dao.ItemDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import org.greenrobot.eventbus.Subscribe

class AddItemController(
    private val itemDao: ItemDao
) {

    @Subscribe
    fun addItem(itemAddedEvent: ItemAddedEvent) {
        val item = itemAddedEvent.newItem
        runBlocking {
            launch {
                withContext(Dispatchers.IO) {
                    itemDao.insertAll(item)
                }
            }
        }
    }
}