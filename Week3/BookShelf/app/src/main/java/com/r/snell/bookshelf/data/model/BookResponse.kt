package com.r.snell.bookshelf.data.model

data class BookResponse(val items: List<BookItem>)

data class BookItem(
    val id: String = "",
    val volumeInfo: VolumeInfo = VolumeInfo()
)

data class VolumeInfo(
    val title: String = "Unknown Title",
    val authors: List<String>? = null,
    val description: String? = null,
    val imageLinks: ImageLinks? = null
)

data class ImageLinks(
    val thumbnail: String = ""
)