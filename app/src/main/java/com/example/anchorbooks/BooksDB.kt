package com.example.anchorbooks

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [AnchorBooks::class, BooksDetail::class], version = 1 )
abstract class BooksDB :RoomDatabase() {
    abstract  fun getDaoAnchorBooks(): DaoAnchorBooks

    companion object {
        @Volatile
        private var INSTANCE: BooksDB? = null

        fun getBooksDB(context: Context): BooksDB {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(context.applicationContext,
                    BooksDB::class.java, "booksDB")
                    .build()
                INSTANCE = instance
                return instance
            }

        }
    }

}