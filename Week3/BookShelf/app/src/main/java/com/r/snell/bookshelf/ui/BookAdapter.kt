package com.r.snell.bookshelf.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.r.snell.bookshelf.R
import com.r.snell.bookshelf.data.model.BookItem

class BookAdapter(private val books: List<BookItem>) :
    RecyclerView.Adapter<BookAdapter.BookViewHolder>() {

    class BookViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val image: ImageView = view.findViewById(R.id.bookImage)
        val title: TextView = view.findViewById(R.id.bookTitle)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.book_item, parent, false)
        return BookViewHolder(view)
    }

    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
        val book = books[position]
        holder.title.text = book.volumeInfo.title
        val imageUrl = book.volumeInfo.imageLinks?.thumbnail
        if (imageUrl != null) {
            Glide.with(holder.itemView).load(imageUrl).into(holder.image)
        }
    }

    override fun getItemCount() = books.size
}