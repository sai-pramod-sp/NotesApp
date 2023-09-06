package com.example.notesapp.feature_node.domain.use_case

import com.example.notesapp.feature_node.domain.model.Note
import com.example.notesapp.feature_node.domain.repository.NoteRepository

class GetNote(
    private val noteRepository: NoteRepository
) {

    suspend operator fun invoke(id: Int): Note?{
        return noteRepository.getNoteById(id)
    }
}