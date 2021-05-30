package de.moyapro.shopping.event

import de.moyapro.shopping.AppState

data class AppStateChangedEvent(
    val newState: AppState
)
