package com.github.fisherman08.micronautbooks.controller.book

import com.github.fisherman08.micronautbooks.controller.ApiPaths
import com.github.fisherman08.micronautbooks.domain.book.BookId
import com.github.fisherman08.micronautbooks.usecase.book.BookDeleteUseCase
import io.micronaut.http.HttpStatus
import io.micronaut.http.annotation.*

@Controller(ApiPaths.Book.delete)
class BookDeleteController(
    private val delete: BookDeleteUseCase
) {
    @Delete
    @Status(HttpStatus.NO_CONTENT)
    fun deleteBook(
        @PathVariable id: String
    ) {
        delete(BookId.fromString(id))
    }

}
