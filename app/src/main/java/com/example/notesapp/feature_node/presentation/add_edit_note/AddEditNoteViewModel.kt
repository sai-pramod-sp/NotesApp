package com.example.notesapp.feature_node.presentation.add_edit_note

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.toArgb
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.notesapp.feature_node.domain.model.InvalidNoteException
import com.example.notesapp.feature_node.domain.model.Note
import com.example.notesapp.feature_node.domain.use_case.NotesUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddEditNoteViewModel @Inject constructor(
    private val notesUseCases: NotesUseCases,
    savedStateHandle: SavedStateHandle
): ViewModel(){

    private val _noteTitle = mutableStateOf(NoteTextFieldState(
        hint = "Enter Title...."
    ))
    val noteTitle: State<NoteTextFieldState> = _noteTitle

    private val _noteContent = mutableStateOf(NoteTextFieldState(
        hint = "Enter Some Content...."
    ))
    val noteContent: State<NoteTextFieldState> = _noteContent

    private val _noteColors = mutableStateOf(Note.notecolors.random().toArgb())
    val noteColor: State<Int> = _noteColors

    private val _evenFlow = MutableSharedFlow<UiEvent>()
    val eventflow = _evenFlow


    private var currentNoteId: Int ?= null

    init {
        savedStateHandle.get<Int>("noteId")?.let { noteId ->
            if(noteId != -1) {
                viewModelScope.launch {
                    notesUseCases.getNote(noteId)?.also { note ->
                        currentNoteId = note.id
                        _noteTitle.value = noteTitle.value.copy(
                            text = note.title,
                            isHintVisible = false
                        )
                        _noteContent.value = _noteContent.value.copy(
                            text = note.content,
                            isHintVisible = false
                        )
                        _noteColors.value = note.color
                    }
                }
            }
        }
    }

    fun onEvent(event: AddEditNoteEvent){
        when(event){
            is AddEditNoteEvent.EnteredTitle -> {
                _noteTitle.value = noteTitle.value.copy(
                    text = event.value
                )
            }
            is AddEditNoteEvent.ChangeTitleFocus -> {
                _noteTitle.value = noteTitle.value.copy(
                    isHintVisible = !event.focusState.isFocused &&
                            noteTitle.value.text.isBlank()
                )
            }
            is AddEditNoteEvent.EnteredContent -> {
                _noteTitle.value = noteTitle.value.copy(
                    text = event.value
                )

            }
            is AddEditNoteEvent.ChangeContentFocus -> {
                _noteTitle.value = noteTitle.value.copy(
                    isHintVisible =  !event.focusState.isFocused &&
                            noteTitle.value.text.isBlank()
                )

            }
            is AddEditNoteEvent.ChangeColor -> {
                _noteColors.value = event.color

            }
            is AddEditNoteEvent.SaveNote -> {
                viewModelScope.launch {
                    try {
                        notesUseCases.addNote(
                            Note(
                                title = noteTitle.value.text,
                                content = noteContent.value.text,
                                timestamp = System.currentTimeMillis(),
                                color = noteColor.value,
                                id = currentNoteId
                            )
                        )
                        _evenFlow.emit(UiEvent.SaveNote)
                    }catch (e: InvalidNoteException){
                        _evenFlow.emit(
                            UiEvent.ShowSnackBar(
                                message = e.message ?: "Couldn't save note"
                            )
                        )
                    }
                }
            }
        }
    }

    sealed class UiEvent{
        data class ShowSnackBar(val message: String): UiEvent()
        object SaveNote: UiEvent()
    }


}