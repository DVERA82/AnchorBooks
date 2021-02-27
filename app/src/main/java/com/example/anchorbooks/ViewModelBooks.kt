package com.example.anchorbooks

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class ViewModelBooks  (application: Application)  : AndroidViewModel(application) {

    private val repository: RepositoryAnchorBooks
    val booksLiveDataFromDB: LiveData<List<AnchorBooks>>



    init {

        val dao = BooksDB.getBooksDB(application).getDaoAnchorBooks()
        repository= RepositoryAnchorBooks(dao)
        viewModelScope.launch {
            repository.getBooksWithCoroutines()
        }
        booksLiveDataFromDB = repository.booksList
    }
    fun getAllDaoAnchorBooks(): LiveData<List<AnchorBooks>> = repository.booksList

    fun getBooksDetail()= viewModelScope.launch {
        repository.converterDetailBooks(booksDetail = id)
    }

    fun getBooksWithCoroutines(id: Int) = viewModelScope.launch {
        repository.converterDetailBooks(id)
    }


    fun getBooksBD(id: String): LiveData<List<BooksDetail>> {
        return repository.getBooksBD(id)
    }

}