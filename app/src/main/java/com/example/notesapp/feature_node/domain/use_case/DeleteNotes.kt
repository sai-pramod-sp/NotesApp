package com.example.notesapp.feature_node.domain.use_case

import com.example.notesapp.feature_node.domain.model.Note
import com.example.notesapp.feature_node.domain.repository.NoteRepository

class DeleteNotes(
    private val noteRepository: NoteRepository
) {

    suspend operator fun invoke(note: Note){
        noteRepository.deleteNote(note)
    }
}