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
import de.moyapro.shopping.action.ActionBar
import de.moyapro.shopping.action.ActionController
import de.moyapro.shopping.itemlist.ItemListController
import de.moyapro.shopping.model.Item
import de.moyapro.shopping.itemlist.ItemListViewModel
import de.moyapro.shopping.additem.AddItemComponent
import de.moyapro.shopping.additem.AddItemController
import de.moyapro.shopping.itemlist.ShoppingListView
import de.moyapro.shopping.repository.ItemRepository
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
        )
            .fallbackToDestructiveMigration()
            .build()
    }
    private val itemRepository: ItemRepository by lazy {
        val respository = db.itemRepository()
        respository
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        EventBus.getDefault().register(ItemListController(viewModel))
        EventBus.getDefault().register(AddItemController(itemRepository))
        EventBus.getDefault().register(ActionController(itemRepository))

        setContent {
            MainView()
        }
    }

    @Composable
    private fun MainView() {
        runBlocking {
            launch {
                lateinit var loadedItems: List<Item>
                withContext(Dispatchers.IO) {
                    loadedItems = itemRepository.getAll()
                }
                viewModel.setItems(loadedItems)
            }
        }
        ShoppingTheme {
            Surface(color = MaterialTheme.colors.background) {
                Column {
                    ShoppingListView(viewModel = viewModel)
                    AddItemComponent()
                    ActionBar()
                }
            }
        }
    }


}




