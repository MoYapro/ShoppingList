package de.moyapro.shopping.itemlist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import de.moyapro.shopping.AppState
import de.moyapro.shopping.model.CartItemRelation

class AppViewModel : ViewModel() {

    private var _state: MutableLiveData<AppState> = MutableLiveData(AppState.PLANNING)
    val state: LiveData<AppState> = _state

    private var _itemList: MutableLiveData<List<CartItemRelation>> = MutableLiveData(emptyList())
    val items: LiveData<List<CartItemRelation>> = _itemList

    fun addItem(item: CartItemRelation) {
        _itemList.value = (_itemList.value!! + listOf(item)).sortedWith(ItemComparator)
    }

    fun removeItem(item: CartItemRelation) {
        _itemList.value = _itemList.value!!.filterNot { it === item }
    }

    fun setItems(items: List<CartItemRelation>) {
        _itemList.value = items.sortedWith(ItemComparator)
    }

    fun removeCheckedItems() {
        _itemList.value = _itemList.value!!.filter { item ->
            !item.cartItem.checked
        }.sortedWith(ItemComparator)
    }

    fun setState(state: AppState) {
        _state.value = state
    }

    val updateItem = { item: CartItemRelation ->
        _itemList.value = _itemList.value!!.map { listItem ->
            if (item.item.itemId == listItem.item.itemId) {
                item
            } else {
                listItem
            }
        }.sortedWith(ItemComparator)
    }

}

object ItemComparator : Comparator<CartItemRelation> {

    override fun compare(item1: CartItemRelation, item2: CartItemRelation): Int {
        return 100 * item1.cartItem.checked.compareTo(item2.cartItem.checked) + item1.item.itemName.compareTo(
            item2.item.itemName
        )
    }

}