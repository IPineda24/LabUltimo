package com.naldana.dummydictionary.data.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.naldana.dummydictionary.data.model.Word

@Dao
interface WordDao {

    @Query("SELECT * FROM  word_table")
    fun getAllWords(): LiveData<List<Word>>

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insertWord(word: List<Word>)

    @Update
    suspend fun updateWord(word: Word)

    @Delete
    suspend fun deleteWord(word: Word)


    

}