package note.data.local

class NoteLocalDataSource {

    private val noteList = mutableListOf<NoteDto>()

    suspend fun insert(noteDto: NoteDto): NoteDto {
        noteList.add(
            noteDto.copy(
                id = noteList.size.toString()
            )
        )
        return noteDto
    }

    suspend fun update(noteDto: NoteDto): NoteDto {
        val index = noteList.indexOfFirst { it.id == noteDto.id }
        noteList[index] = noteDto
        return noteDto
    }

    suspend fun getById(id: String): NoteDto {
        return noteList.first { it.id == id }
    }

    suspend fun getAll(): List<NoteDto> {
        return noteList
    }

    suspend fun delete(id: String) {
        noteList.removeAll { it.id == id }
    }

}