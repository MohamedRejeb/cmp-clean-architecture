package home.ui

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import note.domain.NoteRepository

class HomeViewModel(
    private val noteRepository: NoteRepository,
): ScreenModel {

    private val _state = MutableStateFlow(HomeState())
    val state = _state.asStateFlow()

    fun onEvent(event: HomeEvent) {
        when (event) {
            is HomeEvent.RefreshNotes -> getAllNotes()
        }
    }

    private fun getAllNotes() {
        if (_state.value.isLoading) return

        _state.update { HomeState() }

        screenModelScope.launch {
            noteRepository.getAll()
                .onSuccess { noteList ->
                    _state.update {
                        it.copy(
                            isLoading = false,
                            noteList = noteList,
                        )
                    }
                }
                .onFailure {
                    _state.update {
                        it.copy(
                            isLoading = false,
                            isFailure = true,
                        )
                    }
                }
        }
    }

}