package de.moyapro.shopping.itemlist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import de.moyapro.shopping.model.Item

class ItemListViewModel : ViewModel() {
    private var _itemList: MutableLiveData<List<Item>> = MutableLiveData(emptyList())
    val items: LiveData<List<Item>> = _itemList

    fun addItem(item: Item) {
        _itemList.value = (_itemList.value!! + listOf(item)).sortedWith(ItemComparator)
    }

    fun removeItem(item: Item) {
        _itemList.value = _itemList.value!!.filterNot { it === item }
    }

    fun setItems(items: List<Item>) {
        _itemList.value = items.sortedWith(ItemComparator)
    }

    fun removeCheckedItems() {
        _itemList.value = _itemList.value!!.filter { item ->
            !item.checked
        }.sortedWith(ItemComparator)
    }

    val updateItem = { item: Item ->
        _itemList.value = _itemList.value!!.map { listItem ->
            if (item.itemId == listItem.itemId) {
                item
            } else {
                listItem
            }
        }.sortedWith(ItemComparator)
    }

}

object ItemComparator : Comparator<Item> {

    override fun compare(item1: Item, item2: Item): Int {
        return 100 * item1.checked.compareTo(item2.checked) + item1.itemName.compareTo(item2.itemName)
    }

}