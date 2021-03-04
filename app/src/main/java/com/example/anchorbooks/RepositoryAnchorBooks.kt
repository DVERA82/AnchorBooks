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
                    imageLink = it.imageLink, language = it.language, title = it.title,favourite= it.favourite))
        }
        return listAnchorBooks
    }




    fun converterDetailBooks(id: Int,author:String,country:String,imageLink:String,language:String,
                             link:String,pages:Int, title:String,year:Int,price:Int,lastPrice:Int,
                             delivery:Boolean): List<BooksDetail> {
        val listBooksDetail:MutableList<BooksDetail>  = mutableListOf()
            listBooksDetail.add(
                BooksDetail(
                    id = id,
                    author = author,
                    country = country,
                    imageLink = imageLink,
                    language = language,
                    link = link,
                    pages = pages,
                    title = title,
                    year = year,
                    price = price,
                    lastPrice = lastPrice,
                    delivery = delivery)

            )

        return listBooksDetail
}
    suspend fun getBooksWithCoroutines() {
        Log.d("REPOSITORY", "UTILIZANDO COROUTINES")
        try {
            val response = RetrofitAnchorBooks.retrofitInstance().fetchAnchorBooks()
            when (response.isSuccessful) {
                true -> response.body()?.let {
                    dao.InsertAllDaoAnchorBooks(converter(it.list))
                }

                false -> Log.d("ERROR", " ${response.code()} : ${response.errorBody()}")
            }

        } catch (t: Throwable) {
            Log.e("ERROR COROUTINA", t.message.toString())
        }

    }

    suspend fun getBookDetail(id: Int) {

        try {
            val response = RetrofitAnchorBooks.retrofitInstance().fetchBookDetail(id)
            when (response.isSuccessful) {
                true -> response.body()?.let {
                    dao.InsertBooksDetail(converterDetailBooks(id,it.author,it.country,it.imageLink,
                        it.language,it.link,it.pages,it.title,it.year,it.price,it.lastPrice,it.delivery))
                }
                false -> Log.d("ERROR", " ${response.code()} : ${response.errorBody()}")
            }

        } catch (t: Throwable) {
            Log.e("ERROR COROUTINA", t.message.toString())
        }


    }

    fun getBooksDB(id: Int): LiveData<List<BooksDetail>> {
        return dao.getBookDetail(id)
    }
    suspend fun updateFavouriteBooks(anchorBooks: AnchorBooks) {
        dao.updateAnchorBooks(anchorBooks)
    }

}

