package com.r.snell.bookshelf.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.r.snell.bookshelf.data.model.BookItem
import com.r.snell.bookshelf.repository.BookRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class BookViewModel(private val repository: BookRepository) : ViewModel() {

    private val _books = MutableStateFlow<List<BookItem>>(emptyList())
    val books: StateFlow<List<BookItem>> = _books

    fun search(query: String) {
        viewModelScope.launch {
            try {
                _books.value = repository.searchBooks(query)
            } catch (e: Exception) {
                Log.e("BookViewModel", "Error fetching books", e)
            }
        }
    }
}