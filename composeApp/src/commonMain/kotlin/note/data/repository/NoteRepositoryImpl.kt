package note.data.repository

import note.data.local.NoteLocalDataSource
import note.data.local.NoteDto
import note.domain.NoteModel
import note.domain.NoteRepository

class NoteRepositoryImpl(
    private val local: NoteLocalDataSource,
): NoteRepository {

    override suspend fun insert(noteModel: NoteModel): Result<NoteModel> =
        runCatching {
            local
                .insert(NoteDto.fromModel(noteModel))
                .toModel()
        }

    override suspend fun update(noteModel: NoteModel): Result<NoteModel> =
        runCatching {
            local
                .update(NoteDto.fromModel(noteModel))
                .toModel()
        }

    override suspend fun getById(id: String): Result<NoteModel>  =
        runCatching {
            local
                .getById(id)
                .toModel()
        }

    override suspend fun getAll(): Result<List<NoteModel>>  =
        runCatching {
            local
                .getAll()
                .map { it.toModel() }
        }

    override suspend fun delete(id: String): Result<Unit>  =
        runCatching {
            local
                .delete(id)
        }
}