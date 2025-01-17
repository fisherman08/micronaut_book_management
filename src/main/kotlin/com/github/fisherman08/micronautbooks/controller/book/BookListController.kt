package com.github.fisherman08.micronautbooks.controller.book

import com.github.fisherman08.micronautbooks.controller.ApiPaths
import com.github.fisherman08.micronautbooks.domain.book.Book
import com.github.fisherman08.micronautbooks.domain.book.BookRepository
import com.github.fisherman08.micronautbooks.domain.writer.WriterId
import com.github.fisherman08.micronautbooks.usecase.book.BookListUseCase
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.QueryValue
import java.util.*

@Controller(ApiPaths.Book.list)
class BookListController(
    private val getBookList: BookListUseCase
) {
    @Get
    fun getlist(
        @QueryValue authorIds: List<String>?
    ): List<ResponseBody> {
        return ResponseBody.toResponseBody(getBookList(authorIds?.map { WriterId.fromString(it)}))
    }

    data class ResponseBody(val id: UUID, val title: String, val authors: List<Author>) {
        data class Author(val id: UUID, val name: String)
        companion object {
            fun toResponseBody(books: List<Book>): List<ResponseBody> =
                books.map {
                    ResponseBody(
                        id = it.id.value,
                        title = it.title.value,
                        authors = it.authors.map { au -> Author(id = au.id.value, name = au.name.value) }
                    )
                }
        }
    }
}
