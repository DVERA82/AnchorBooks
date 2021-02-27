package com.example.anchorbooks

import android.util.Log
import androidx.lifecycle.LiveData

class RepositoryAnchorBooks (private val dao: DaoAnchorBooks) {

    val booksList: LiveData<List<AnchorBooks>> = dao.getAllbooks()

    fun converter(converter: List<DataClassBooks>): List<AnchorBooks> {
        val listAnchorBooks = mutableListOf<AnchorBooks>()
        converter.map {
            listAnchorBooks.add(
                AnchorBooks(
                    id = it.id, author = it.author, country = it.country,
                    imageLink = it.imageLink, language = it.language, title = it.title
                )
            )
        }
        return listAnchorBooks
    }


    fun converterDetailBooks(BookDetail: List<DataClassDetail>, booksDetail: Int)
            : List<BooksDetail> {
        val listBooksDetail = mutableListOf<BooksDetail>()
        listBooksDetail.map {
            listBooksDetail.add(
                BooksDetail(
                    id = booksDetail,
                    author = it.author,
                    country = it.country,
                    imageLink = it.imageLink,
                    language = it.language,
                    link = it.link,
                    pages = it.pages,
                    title = it.title,
                    year = it.year,
                    price = it.price,
                    lastPrice = it.lastPrice,
                    delivery = it.delivery))


        }
        return listBooksDetail
    }
    suspend fun getBooksWithCoroutines() {
        Log.d("REPOSITORY", "UTILIZANDO COROUTINES")
        try {
            val response = RetrofitAnchorBooks.retrofitInstance().fetchAnchorBooks()
            when (response.isSuccessful) {
                true -> response.body()?.let {
                    dao.InsertAllDaoAnchorBook(converter(it.list))
                }

                false -> Log.d("ERROR", " ${response.code()} : ${response.errorBody()}")
            }

        } catch (t: Throwable) {
            Log.e("ERROR COROUTINA", t.message.toString())
        }

    }

    suspend fun getbookDetail(id: Int) {

        try {
            val response = RetrofitAnchorBooks.retrofitInstance().fetchBookDetail(id)
            when (response.isSuccessful) {
                true -> response.body()?.let {
                    //Log.d("repo","$(it)")
                    dao.InsertBooksDetail(converterDetailBooks(it.id,))
                }
                false -> Log.d("ERROR", " ${response.code()} : ${response.errorBody()}")
            }

        } catch (t: Throwable) {
            Log.e("ERROR COROUTINA", t.message.toString())
        }


    }

    fun getBooksBD(id: String): LiveData<List<BooksDetail>> {
        return dao.getBookDetail(id)
    }


}

