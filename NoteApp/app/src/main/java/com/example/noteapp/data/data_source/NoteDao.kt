package com.example.noteapp.data.data_source

import androidx.room.*
import com.example.noteapp.domain.model.Note
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteDao {

    @Query("SELECT * FROM Note ORDER BY date DESC")
    fun observeNotes(): Flow<List<Note>>

    @Query("SELECT * FROM Note ORDER BY date DESC")
    suspend fun getAll(): List<Note>

    @Query("SELECT * FROM Note Where uid = :uid")
    suspend fun get(uid: Int): Note

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(note: Note)

    @Update
    suspend fun update(note: Note)

    @Delete
    suspend fun delete(note: Note)

    @Query("DELETE FROM Note")
    suspend fun deleteAll()

}