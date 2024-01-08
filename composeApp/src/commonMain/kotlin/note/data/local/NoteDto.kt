package note.data.local

import note.domain.NoteModel

data class NoteDto(
    val id: String,
    val title: String,
    val description: String,
) {
    fun toModel() = NoteModel(
        id = id,
        title = title,
        description = description,
    )

    companion object {
        fun fromModel(noteModel: NoteModel) = NoteDto(
            id = noteModel.id,
            title = noteModel.title,
            description = noteModel.description,
        )
    }
}