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
import de.moyapro.shopping.additem.AddItemComponent
import de.moyapro.shopping.additem.AddItemController
import de.moyapro.shopping.event.ReloadEvent
import de.moyapro.shopping.itemlist.ItemListController
import de.moyapro.shopping.itemlist.AppViewModel
import de.moyapro.shopping.itemlist.ShoppingListView
import de.moyapro.shopping.itemlist.UpdateItemController
import de.moyapro.shopping.repository.CartItemRepository
import de.moyapro.shopping.repository.ItemRepository
import de.moyapro.shopping.ui.theme.ShoppingTheme
import org.greenrobot.eventbus.EventBus


class MainActivity : ComponentActivity() {

    private val viewModel: AppViewModel by viewModels()
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

    private val cartItemRepository: CartItemRepository by lazy {
        val respository = db.cartItemRepository()
        respository
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        EventBus.getDefault().register(ItemListController(cartItemRepository, viewModel))
        EventBus.getDefault().register(AddItemController(itemRepository))
        EventBus.getDefault().register(UpdateItemController(itemRepository, viewModel))
        EventBus.getDefault().register(ActionController(itemRepository, viewModel))
        setContent {
            MainView()
        }
    }

    @Composable
    private fun MainView() {
        EventBus.getDefault().post(ReloadEvent)
        ShoppingTheme {
            Surface(color = MaterialTheme.colors.background) {
                Column {
                    ActionBar(viewModel)
                    ShoppingListView(viewModel)
                    AddItemComponent(itemRepository)
                }
            }
        }
    }


}




