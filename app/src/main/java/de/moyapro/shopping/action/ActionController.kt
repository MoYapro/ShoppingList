package de.moyapro.shopping.action

import de.moyapro.shopping.event.RemoveCheckedEvent
import de.moyapro.shopping.repository.ItemRepository
import org.greenrobot.eventbus.Subscribe

class ActionController(itemRepository: ItemRepository) {

    @Subscribe
    fun clearChecked(removeCheckedEvent: RemoveCheckedEvent) {

    }

}