package com.example.notesapp.feature_node.domain.use_case

import com.example.notesapp.feature_node.domain.model.InvalidNoteException
import com.example.notesapp.feature_node.domain.model.Note
import com.example.notesapp.feature_node.domain.repository.NoteRepository
import kotlin.jvm.Throws

class AddNote(
    private val noteRepository: NoteRepository
) {

    @Throws(InvalidNoteException::class)
    suspend operator fun invoke(note: Note){

        if(note.title.isBlank()){
            throw InvalidNoteException("The note title can't be Empty!!..")
        }
        if(note.content.isBlank()){
            throw InvalidNoteException("The Content of the note can't be Empty!!...")
        }
        noteRepository.insertNote(note)
    }
}