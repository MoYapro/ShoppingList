package de.moyapro.shopping

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequesterModifier
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.unit.Dp
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
//        runBlocking {
//            launch { viewModel.setItems(itemRepository.getAll()) }
//        }
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
        val items =
            if ("" == textState.value) {
                emptyList()
            } else {
                listOf(
                    "Apfel",
                    "Milch",
                    "Butter",
                    "Brot",
                    "Bier",
                    "Brei",
                    "Banane",
                ).filter { it.contains(textState.value) }
            }
            Column(
                modifier = Modifier.border(
                    width = Dp(1F), brush = SolidColor(Color.Black), shape = RectangleShape
                )
                    .fillMaxWidth()
            ) {
                items.forEach { suggestionText ->
                    ClickableText(text = AnnotatedString(suggestionText), onClick = {
                        viewModel.addItem(
                            Item(name = suggestionText)
                        )
                        textState.value = ""
                    })
                }
            }
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
                    textState.value = ""
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
