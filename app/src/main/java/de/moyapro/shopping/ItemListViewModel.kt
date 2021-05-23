package de.moyapro.shopping

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import de.moyapro.shopping.model.Item
import de.moyapro.shopping.model.ItemRepository

class ItemListViewModel : ViewModel() {
    lateinit var repository: ItemRepository
    private var _itemList: MutableLiveData<List<Item>> = MutableLiveData(emptyList())
    val items: LiveData<List<Item>> = _itemList

    fun addItem(item: Item) {
        _itemList.value = (_itemList.value!! + listOf(item)).sortedWith(ItemComparator)
        if(null == repository.loadAllByIds(item.id)) {
            repository.insertAll(item)
        }
    }

    fun removeItem(item: Item) {
        _itemList.value = _itemList.value!!.filterNot { it === item }
    }

    fun setItems(items: List<Item>) {
        _itemList.value = items.sortedWith(ItemComparator)
    }

    val updateItem = { item: Item ->
        _itemList.value = _itemList.value!!.map { listItem ->
            if (item.id == listItem.id) {
                item
            } else {
                listItem
            }
        }.sortedWith(ItemComparator)
    }

}

object ItemComparator : Comparator<Item> {

    override fun compare(item1: Item, item2: Item): Int {
        return 100 * item1.checked.compareTo(item2.checked) + item1.name.compareTo(item2.name)
    }

}