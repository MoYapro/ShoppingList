package de.moyapro.shopping.itemlist

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Divider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import de.moyapro.shopping.AppState
import de.moyapro.shopping.model.Item

@Composable
fun ShoppingListView(viewModel: AppViewModel, modifier: Modifier = Modifier) {
    val itemList: List<Item> by viewModel.items.observeAsState(listOf())
    val state by remember { mutableStateOf(viewModel.state.value ?: AppState.PLANNING) }
    LazyColumn(modifier = modifier) {
        items(items = itemList) { theItem ->
            ItemComponent(item = theItem, state = state, update = viewModel.updateItem)
            Divider(color = Color.Black)
        }
    }
}
