package com.example.notesapp.feature_node.presentation.notes

import androidx.lifecycle.ViewModel
import com.example.notesapp.feature_node.domain.use_case.NotesUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class NotesViewModel @Inject constructor(
    private val notesUseCases: NotesUseCases
): ViewModel(){

}