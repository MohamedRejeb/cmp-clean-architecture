package home.ui

import note.domain.NoteModel

data class HomeState(
    val noteList: List<NoteModel> = emptyList(),
    val isLoading: Boolean = false,
    val isFailure: Boolean = false,
)