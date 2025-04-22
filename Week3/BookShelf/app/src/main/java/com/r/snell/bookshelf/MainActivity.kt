package com.r.snell.bookshelf

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.tooling.preview.Preview
import com.r.snell.bookshelf.repository.BookRepository
import com.r.snell.bookshelf.ui.screen.AppNavHost
import com.r.snell.bookshelf.viewmodel.BookViewModel
import com.r.snell.bookshelf.ui.theme.BookShelfTheme
import com.r.snell.bookshelf.data.model.BookItem
import com.r.snell.bookshelf.data.model.ImageLinks
import com.r.snell.bookshelf.data.model.VolumeInfo
import com.r.snell.bookshelf.viewmodel.BookViewModelFactory

class MainActivity : ComponentActivity() {
    private val viewModel: BookViewModel by viewModels {
        BookViewModelFactory(BookRepository())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BookShelfTheme {
                AppNavHost(viewModel)
            }
        }
    }
}

@Composable
fun previewBookViewModel(): BookViewModel {
    val fakeRepository = object : BookRepository() {
        override suspend fun searchBooks(query: String): List<BookItem> {
            return listOf(
                BookItem(
                    id = "1",
                    volumeInfo = VolumeInfo(
                        title = "Preview Book",
                        authors = listOf("Tom Clancy"),
                        imageLinks = ImageLinks("https://via.placeholder.com/150")
                    )
                )
            )
        }
    }
    return remember { BookViewModel(fakeRepository) }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    BookShelfTheme {
        AppNavHost(viewModel = previewBookViewModel())
    }
}