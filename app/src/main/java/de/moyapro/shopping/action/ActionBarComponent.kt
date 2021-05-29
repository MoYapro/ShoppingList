package de.moyapro.shopping.action

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import de.moyapro.shopping.event.ReloadEvent
import de.moyapro.shopping.event.RemoveCheckedEvent
import org.greenrobot.eventbus.EventBus

@Composable
fun ActionBar() {
    Row {
        RemoveCheckedButton()
        ReloadFromDatabaseButton()
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