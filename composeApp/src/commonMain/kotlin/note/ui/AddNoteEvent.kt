package note.ui

import note.domain.NoteModel

sealed interface AddNoteEvent {

    data class AddNote(val note: NoteModel): AddNoteEvent

}