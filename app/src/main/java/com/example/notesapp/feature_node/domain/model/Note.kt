package com.example.notesapp.feature_node.domain.model
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.notesapp.ui.theme.BabyBlue
import com.example.notesapp.ui.theme.LightGreen
import com.example.notesapp.ui.theme.RedOrange
import com.example.notesapp.ui.theme.RedPink
import com.example.notesapp.ui.theme.Violet


/*A Room entity includes fields for each column in the corresponding table in the database,
including one or more columns that make up the primary key*/
@Entity
data class Note(

    val title: String,
    val content: String,
    val timestamp: Long,
    val color: Int,
    @PrimaryKey val id: Int ?= null

){
    companion object{
        val notecolors = listOf(RedOrange, LightGreen, Violet, BabyBlue, RedPink)
    }
}
