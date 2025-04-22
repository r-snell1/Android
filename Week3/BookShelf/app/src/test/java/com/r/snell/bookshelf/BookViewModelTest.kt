package com.r.snell.bookshelf

import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.r.snell.bookshelf.data.model.BookItem
import com.r.snell.bookshelf.data.model.ImageLinks
import com.r.snell.bookshelf.data.model.VolumeInfo
import com.r.snell.bookshelf.repository.BookRepository
import com.r.snell.bookshelf.viewmodel.BookViewModel
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.junit.Assert.assertEquals
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit
import java.util.concurrent.TimeoutException

@ExperimentalCoroutinesApi
class BookViewModelTest {

    private lateinit var viewModel: BookViewModel
    private val repository: BookRepository = mockk()

    @Before
    fun setUp() {
        viewModel = BookViewModel(repository)
    }

    @Test
    fun `searchBooks returns list`() = runTest {
        val mockBooks = listOf(BookItem("1", VolumeInfo("Title", ImageLinks("url"))))
        coEvery { repository.searchBooks("android") } returns mockBooks

        viewModel.search("android")

        val result = viewModel.books.getOrAwaitValue()
        assertEquals("Title", result.first().volumeInfo.title)
    }

    @Suppress("UNCHECKED_CAST")
    fun <T> LiveData<T>.getOrAwaitValue(
        time: Long = 2,
        timeUnit: TimeUnit = TimeUnit.SECONDS
    ): T {
        var data: T? = null
        val latch = CountDownLatch(1)

        val observer = object : Observer<T> {
            override fun onChanged(value: T) {
                val t = null
                data = t
                latch.countDown()
                this@getOrAwaitValue.removeObserver(this)
            }

        }

        this.observeForever(observer)
        if (!latch.await(time, timeUnit)) {
            throw TimeoutException("LiveData value was never set.")
        }

        return data ?: throw IllegalStateException("LiveData emitted null")
    }
}