package com.example.notesapp.feature_node.presentation.add_edit_note

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.toArgb
import com.example.notesapp.feature_node.domain.model.Note
import kotlinx.coroutines.flow.MutableSharedFlow

data class NoteTextFieldState(

    val text: String = "",
    val hint: String = "",
    val isHintVisible: Boolean = true
)
