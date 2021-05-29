package de.moyapro.shopping.itemlist

import androidx.lifecycle.viewmodel.compose.viewModel
import de.moyapro.shopping.event.AddItemEvent
import de.moyapro.shopping.event.RemoveCheckedEvent
import org.greenrobot.eventbus.Subscribe

class ItemListController(
    val viewModel: ItemListViewModel
) {

    @Subscribe
    fun addItem(addItemEvent: AddItemEvent) {
        viewModel.addItem(addItemEvent.newItem)
    }

    @Subscribe
    fun clearChecked(removeCheckedEvent: RemoveCheckedEvent) {
        viewModel.removeCheckedItems()
    }

}