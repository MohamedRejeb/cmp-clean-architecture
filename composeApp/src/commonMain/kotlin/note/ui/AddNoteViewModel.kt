package note.ui

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import note.domain.NoteRepository

class AddNoteViewModel(
    private val noteRepository: NoteRepository,
): ScreenModel {

    private val _state = MutableStateFlow(AddNoteState())
    val state = _state.asStateFlow()

    fun onEvent(event: AddNoteEvent) {
        when (event) {
            is AddNoteEvent.AddNote -> addNote(event)
        }
    }

    private fun addNote(event: AddNoteEvent.AddNote) {
        if (_state.value.isLoading) return

        _state.update {
            it.copy(isLoading = true)
        }

        screenModelScope.launch {
            noteRepository.insert(event.note)
                .onSuccess {
                    _state.update {
                        it.copy(
                            isLoading = false,
                            isSuccess = true,
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