package note.di

import note.data.local.NoteLocalDataSource
import note.data.repository.NoteRepositoryImpl
import note.domain.NoteRepository

object NoteModule {
    val noteRepository: NoteRepository by lazy {
        NoteRepositoryImpl(local = NoteLocalDataSource())
    }
}