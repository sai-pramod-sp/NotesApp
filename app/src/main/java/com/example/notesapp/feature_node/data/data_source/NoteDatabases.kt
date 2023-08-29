package com.example.notesapp.feature_node.data.data_source
import com.example.notesapp.feature_node.domain.model.Note
import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [Note::class],
    version = 1
)
abstract class NoteDatabases: RoomDatabase() {

    abstract val noteDao: NoteDao
}