package com.r.snell.bookshelf.data.remote

import com.r.snell.bookshelf.data.model.BookResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface GoogleBooksApi {
    @GET("volumes")
    suspend fun searchBooks(
        @Query("q") query: String,
        @Query("startIndex") startIndex: Int = 0,
        @Query("maxResults") maxResults: Int = 20
    ): BookResponse

}
