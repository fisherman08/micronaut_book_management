package com.github.fisherman08.micronautbooks.controller.book

import com.github.fisherman08.micronautbooks.controller.ApiPaths
import com.github.fisherman08.micronautbooks.domain.book.BookId
import com.github.fisherman08.micronautbooks.domain.book.BookTitle
import com.github.fisherman08.micronautbooks.domain.writer.WriterId
import com.github.fisherman08.micronautbooks.usecase.book.BookUpdateUseCase
import io.micronaut.http.annotation.*
import java.util.*

@Controller(ApiPaths.Book.update)
class BookUpdateController(
    private val update: BookUpdateUseCase
) {
    @Post
    fun updateBook(
        @PathVariable id: String,
        @Body body: RequestBody
    ): ResponseBody {
        val book = update(
            BookId.fromString(id),
            BookTitle.fromString(body.title),
            body.authorIds.map { WriterId.fromString(it)}
        )

        return ResponseBody(
            id = book.id.value,
            title = book.title.value,
            authors = book.authors.map { ResponseBody.Author(it.id.value, it.name.value) }
        )
    }

    data class RequestBody(val title: String, val authorIds: List<String>)
    data class ResponseBody(
        val id: UUID,
        val title: String,
        val authors: List<Author>
    ) {
        data class Author(val id: UUID, val name: String)
    }
}
