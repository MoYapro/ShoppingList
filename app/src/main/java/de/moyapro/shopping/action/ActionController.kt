package de.moyapro.shopping.action

import de.moyapro.shopping.event.AppStateChangedEvent
import de.moyapro.shopping.event.ItemCheckedEvent
import de.moyapro.shopping.event.RemoveCheckedEvent
import de.moyapro.shopping.itemlist.AppViewModel
import de.moyapro.shopping.dao.CartItemDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import org.greenrobot.eventbus.Subscribe

class ActionController(
    private val cartItemDao: CartItemDao,
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
                    val checkedItems = cartItemDao.getChecked()
                    cartItemDao.removeAll(checkedItems.map { it.cartItem })
                }
            }
        }
    }

    @Subscribe
    fun checkItem(itemCheckedEventEvent: ItemCheckedEvent) {
        runBlocking {
            launch {
                withContext(Dispatchers.IO) {
                    cartItemDao.updateAll(itemCheckedEventEvent.item.cartItem)
                    cartItemDao.updateAll(itemCheckedEventEvent.item.item)
                }
            }
        }
    }
}