package de.moyapro.shopping

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.room.Room
import de.moyapro.shopping.itemlist.ItemListController
import de.moyapro.shopping.model.Item
import de.moyapro.shopping.itemlist.ReadItemsRepository
import de.moyapro.shopping.itemlist.ItemListViewModel
import de.moyapro.shopping.ui.components.AddItemComponent
import de.moyapro.shopping.itemlist.ShoppingListView
import de.moyapro.shopping.ui.theme.ShoppingTheme
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import org.greenrobot.eventbus.EventBus


class MainActivity : ComponentActivity() {

    private val viewModel: ItemListViewModel by viewModels()
    private val db by lazy {
        Room.databaseBuilder(
            applicationContext,
            ShoppingDatabase::class.java, "ShoppingDatabase"
        ).build()
    }
    private val itemRepository: ReadItemsRepository by lazy {
        val respository = db.itemRepository()
        viewModel.repository = respository
        respository
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val itemListController = ItemListController(viewModel)
        EventBus.getDefault().register(itemListController)

        setContent {
            MainView()
        }
    }

    @Composable
    private fun MainView() {
        runBlocking {
            launch {
                lateinit var x: List<Item>
                withContext(Dispatchers.IO) {
                    x = itemRepository.getAll()
                }
                viewModel.setItems(x)
            }
        }
        ShoppingTheme {
            Surface(color = MaterialTheme.colors.background) {
                Column {
                    ShoppingListView(viewModel = viewModel)
                    AddItemComponent(viewModel = viewModel)
                }
            }
        }
    }


}




