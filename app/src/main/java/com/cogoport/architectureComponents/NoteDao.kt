//  contains methods to access databasec
package com.cogoport.architectureComponents

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.*

@Dao
interface NoteDao{
    @Insert
    fun insert(note: Note)

    @Query("SELECT * FROM notes")
    fun getAllNotes(): LiveData<List<Note>>

    @Query("SELECT * FROM notes WHERE id=:noteId")
    fun getNote(noteId: String): LiveData<Note>

    @Update
    fun update(note: Note)

    @Delete
    fun delete(note: Note): Int
}