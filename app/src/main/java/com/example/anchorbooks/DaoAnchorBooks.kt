package com.example.anchorbooks

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface DaoAnchorBooks {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun InsertAllDaoAnchorBooks(list: List<AnchorBooks>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun InsertBooksDetail(list: List<BooksDetail>)


    @Query("SELECT * FROM books_table")
    fun getAllbooks(): LiveData<List<AnchorBooks>>

    @Query("SELECT * FROM detail_table WHERE id = :id")
    fun getBookDetail(id:Int) :LiveData<List<BooksDetail>>

    @Update
    suspend fun updateAnchorBooks(anchorBooks: AnchorBooks)
}