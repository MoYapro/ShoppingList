package de.moyapro.shopping

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.room.Room
import de.moyapro.shopping.model.Item
import de.moyapro.shopping.model.ItemRepository
import de.moyapro.shopping.model.ShoppingDatabase
import de.moyapro.shopping.ui.theme.ShoppingTheme


class MainActivity : ComponentActivity() {

    private val viewModel: ItemListViewModel by viewModels()
    private val db by lazy {
        Room.databaseBuilder(
            applicationContext,
            ShoppingDatabase::class.java, "ShoppingDatabase"
        ).build()
    }
    private val itemRepository: ItemRepository by lazy {
        val respository = db.itemRepository()
        viewModel.repository = respository
        respository
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainView()
        }
    }

    @Composable
    private fun MainView() {
        viewModel.setItems(itemRepository.getAll())
        ShoppingTheme {
            Surface(color = MaterialTheme.colors.background) {
                Column {
                    ItemView(viewModel = viewModel)
                    AddItemView(viewModel = viewModel)
                }
            }
        }
    }

    @Composable
    private fun AddItemView(viewModel: ItemListViewModel) {
        val textState = remember { mutableStateOf("") }
        Row {
            TextField(
                value = textState.value,
                onValueChange = { newText ->
                    textState.value = newText
                }
            )
            Button(
                onClick = {
                    viewModel.addItem(
                        Item(name = textState.value)
                    )
                },
                modifier = Modifier.clip(shape = CircleShape)
            ) {
                Text("+")
            }
        }
    }

}

@Composable
private fun ItemView(viewModel: ItemListViewModel, modifier: Modifier = Modifier) {
    val itemList: List<Item> by viewModel.items.observeAsState(listOf())
    Column {
        ItemList(modifier, itemList, viewModel)
    }
}

@Composable
private fun ItemList(
    modifier: Modifier,
    itemList: List<Item>,
    viewModel: ItemListViewModel
) {
    LazyColumn(modifier = modifier) {
        items(items = itemList) { theItem ->
            ItemView(item = theItem, update = viewModel.updateItem)
            Divider(color = Color.Black)
        }
    }
}

@Composable
private fun ItemView(item: Item, update: (Item) -> Unit) {
    val backgroundColor by animateColorAsState(
        targetValue = if (item.checked) Color.Green else Color.Transparent
    )
    Text(
        text = item.name,
        modifier = Modifier
            .fillMaxWidth()
            .background(color = backgroundColor)
            .clickable(onClick = { update(Item(item.id, item.name, !item.checked)) })
    )
}
