package com.github.fisherman08.micronautbooks.controller.book

import com.github.fisherman08.micronautbooks.controller.ApiPaths
import com.github.fisherman08.micronautbooks.domain.book.BookTitle
import com.github.fisherman08.micronautbooks.domain.writer.WriterId
import com.github.fisherman08.micronautbooks.usecase.book.BookRegisterUseCase
import io.micronaut.http.annotation.Body
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Post
import java.util.*

@Controller(ApiPaths.Book.register)
class BookRegisterController(
    private val register: BookRegisterUseCase
) {
    @Post
    fun registerBook(@Body body: RequestBody): ResponseBody {
        val book = register(
            BookTitle.fromString(body.title),
            body.authorIds.map { WriterId.fromString(it) }
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
