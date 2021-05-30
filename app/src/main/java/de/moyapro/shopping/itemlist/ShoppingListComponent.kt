package de.moyapro.shopping.itemlist

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Divider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import de.moyapro.shopping.AppState
import de.moyapro.shopping.model.CartItemRelation

@Composable
fun ShoppingListView(viewModel: AppViewModel, modifier: Modifier = Modifier) {
    val itemList: List<CartItemRelation> by viewModel.items.observeAsState(listOf())
    val appState = viewModel.state.observeAsState(initial = AppState.PLANNING)
    LazyColumn(modifier = modifier) {
        items(items = itemList) { theItem ->
    when(appState.value) {
        AppState.PLANNING -> EditItemComponent(cartItem = theItem, update = viewModel.updateItem)
        AppState.SHOPPING -> ShoppingItemComponent(cartItem = theItem, update = viewModel.updateItem)
    }
            Divider(color = Color.Black)
        }
    }
}
