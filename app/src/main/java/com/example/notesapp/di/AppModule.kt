package com.example.notesapp.di

import android.app.Application
import androidx.room.Room
import com.example.notesapp.feature_node.data.data_source.NoteDatabases
import com.example.notesapp.feature_node.data.repository.NoteRepositoryImpl
import com.example.notesapp.feature_node.domain.repository.NoteRepository
import com.example.notesapp.feature_node.domain.use_case.DeleteNotes
import com.example.notesapp.feature_node.domain.use_case.GetNotes
import com.example.notesapp.feature_node.domain.use_case.NotesUseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideNoteDatabase(app: Application): NoteDatabases{
        return Room.databaseBuilder(
            app,
            NoteDatabases::class.java,
            NoteDatabases.DATABASE_NAME
        ).build()
    }

    @Provides
    @Singleton
    fun provideNoteRepository(db: NoteDatabases): NoteRepository{
        return NoteRepositoryImpl(db.noteDao)
    }

    @Provides
    @Singleton
    fun provideNoteUseCases(repository: NoteRepository): NotesUseCases{
        return NotesUseCases(
            getNotes = GetNotes(repository),
            deleteNotes = DeleteNotes(repository)
        )
    }
}