package home.ui

sealed interface HomeEvent {

    data object RefreshNotes: HomeEvent
}