package de.moyapro.shopping.itemlist

import de.moyapro.shopping.event.AddItemEvent
import org.greenrobot.eventbus.Subscribe

class ItemListController(
    val viewModel: ItemListViewModel
) {

    @Subscribe
    fun addItem(addItemEvent: AddItemEvent) {
        viewModel.addItem(addItemEvent.newItem)
        //        runBlocking {
//            launch {
//                if (null == repository.loadAllByIds(item.id)) {
//                    repository.insertAll(item)
//                }
//            }
//        }
    }

}