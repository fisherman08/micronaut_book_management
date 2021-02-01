package com.github.fisherman08.micronautbooks.controller.book

import com.fasterxml.jackson.annotation.JsonInclude
import com.github.fisherman08.micronautbooks.controller.ApiPaths
import com.github.fisherman08.micronautbooks.domain.book.BookId
import com.github.fisherman08.micronautbooks.usecase.book.BookInfoUseCase
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.PathVariable
import java.util.*

@Controller(ApiPaths.Book.update)
class BookInfoController(
    private val getInfo: BookInfoUseCase
) {
    @Get
    fun getBookInfo(
        @PathVariable id: String
    ): ResponseBody {
        val book = getInfo(BookId.fromString(id))

        return ResponseBody(
            id = book.id.value,
            title = book.title.value,
            authors = book.authors.map { au -> ResponseBody.Author(id = au.id.value, name = au.name.value) }
        )
    }

    @JsonInclude(JsonInclude.Include.ALWAYS)
    data class ResponseBody(val id: UUID, val title: String, val authors: List<Author>) {
        data class Author(val id: UUID, val name: String)
    }
}
