package com.r.snell.bookshelf.ui.screen

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.r.snell.bookshelf.viewmodel.BookViewModel

@Composable
fun AppNavHost(viewModel: BookViewModel) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "books") {
        composable("books") {
            BookListScreen(viewModel, navController)
        }
        composable("detail/{bookId}") { backStackEntry ->
            val bookId = backStackEntry.arguments?.getString("bookId")
            BookDetailScreen(
                bookId = bookId,
                viewModel = viewModel,
                navController = navController
            )
        }
    }
}
