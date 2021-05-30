package de.moyapro.shopping.itemlist

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import de.moyapro.shopping.model.Item

@Composable
fun ShoppingListView(viewModel: AppViewModel, modifier: Modifier = Modifier) {
    val itemList: List<Item> by viewModel.items.observeAsState(listOf())
    LazyColumn(modifier = modifier) {
        items(items = itemList) { theItem ->
            ItemComponent(item = theItem, update = viewModel.updateItem)
            Divider(color = Color.Black)
        }
    }
}
