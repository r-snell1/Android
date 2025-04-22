package com.r.snell.bookshelf.repository

import com.r.snell.bookshelf.data.model.BookItem
import com.r.snell.bookshelf.data.remote.RetrofitClient

open class BookRepository {
    private val api = RetrofitClient.create()

    open suspend fun searchBooks(query: String): List<BookItem> {
        return try{
            api.searchBooks(query).items
        } catch (e: Exception) {
            emptyList()
        }
    }
}