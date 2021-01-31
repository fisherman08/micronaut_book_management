package com.github.fisherman08.micronautbooks.controller.book

import com.github.fisherman08.micronautbooks.controller.ApiPaths
import com.github.fisherman08.micronautbooks.domain.book.Book
import com.github.fisherman08.micronautbooks.domain.book.BookRepository
import com.github.fisherman08.micronautbooks.usecase.book.BookListUseCase
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import java.util.*

@Controller(ApiPaths.Book.list)
class BookListController(
    private val getBookList: BookListUseCase
) {
    @Get
    fun getlist(): List<ResponseBody> = ResponseBody.toResponseBody(getBookList())

    data class ResponseBody(val id: UUID, val title: String) {
        companion object {
            fun toResponseBody(books: List<Book>): List<ResponseBody> =
                books.map { ResponseBody(id = it.id.value, title = it.title.value) }
        }
    }
}
