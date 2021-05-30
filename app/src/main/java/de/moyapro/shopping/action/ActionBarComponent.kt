package de.moyapro.shopping.action

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import de.moyapro.shopping.AppState
import de.moyapro.shopping.AppState.*
import de.moyapro.shopping.event.AppStateChangedEvent
import de.moyapro.shopping.event.ReloadEvent
import de.moyapro.shopping.event.RemoveCheckedEvent
import de.moyapro.shopping.itemlist.AppViewModel
import org.greenrobot.eventbus.EventBus


@Composable
fun ActionBar(viewModel: AppViewModel) {
    var state by remember { mutableStateOf(viewModel.state.value ?: PLANNING) }
    Row {
        RemoveCheckedButton()
        ReloadFromDatabaseButton()
        val update: (AppState) -> Unit = { newState ->
            state = newState
            viewModel.setState(newState)
        }
        when (state) {
            PLANNING -> GoShoppingButton(update)
            SHOPPING -> DoPlanningButton(update)
        }
    }
}

@Composable
private fun RemoveCheckedButton() {
    Button(
        onClick = {
            EventBus.getDefault().post(
                RemoveCheckedEvent
            )
        },
        modifier = Modifier.clip(shape = CircleShape)
    ) {
        Text("Clear")
    }
}

@Composable
private fun ReloadFromDatabaseButton() {
    Button(
        onClick = {
            EventBus.getDefault().post(
                ReloadEvent
            )
        },
        modifier = Modifier.clip(shape = CircleShape)
    ) {
        Text("Reload")
    }
}

@Composable
private fun GoShoppingButton(update: (AppState) -> Unit) {
    Button(
        onClick = {
            update(SHOPPING)
//            EventBus.getDefault().post(
//                AppStateChangedEvent(newState = SHOPPING)
//            )
        },
        modifier = Modifier.clip(shape = CircleShape)
    ) {
        Text("Shop")
    }
}

@Composable
private fun DoPlanningButton(update: (AppState) -> Unit) {
    Button(
        onClick = {
            update(PLANNING)
//            EventBus.getDefault().post(
//                AppStateChangedEvent(newState = PLANNING)
//            )
        },
        modifier = Modifier.clip(shape = CircleShape)
    ) {
        Text("Plan")
    }
}