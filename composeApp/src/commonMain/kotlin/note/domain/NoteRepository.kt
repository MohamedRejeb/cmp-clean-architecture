package note.domain

interface NoteRepository {

    suspend fun insert(noteModel: NoteModel): Result<NoteModel>

    suspend fun update(noteModel: NoteModel): Result<NoteModel>

    suspend fun getById(id: String): Result<NoteModel>

    suspend fun getAll(): Result<List<NoteModel>>

    suspend fun delete(id: String): Result<Unit>

}