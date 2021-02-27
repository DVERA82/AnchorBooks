package com.example.anchorbooks

import androidx.lifecycle.LiveData
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

interface DaoAnchorBooks {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun InsertAllDaoAnchorBook(list: List<AnchorBooks>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun InsertBooksDetail(list: List<BooksDetail>)


    @Query("SELECT * FROM books_table")
    fun getAllbooks(): LiveData<List<AnchorBooks>>

    @Query("SELECT * FROM detail_table WHERE id = :id")
    fun getBookDetail(id:Int) :LiveData<List<BooksDetail>>
}