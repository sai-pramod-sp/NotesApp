package com.example.notesapp.feature_node.presentation.Util

sealed class Screen(val route: String) {
    object NotesScreen: Screen("notes_screen")
//    object AddEditNoteScreen: Screen("add_edit_notes_screen")
    object AddEditNoteScreen: Screen("add_edit_notes_screen")
}