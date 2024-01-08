package note.domain

data class NoteModel(
    val id: String,
    val title: String,
    val description: String,
) {
    companion object {
        val empty = NoteModel(
            id = "",
            title = "",
            description = "",
        )
    }
}
