package com.github.fisherman08.micronautbooks.controller.book

import com.github.fisherman08.micronautbooks.controller.ApiPaths
import com.github.fisherman08.micronautbooks.domain.book.Book
import com.github.fisherman08.micronautbooks.usecase.book.BookListUseCase
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get

@Controller(ApiPaths.Book.list)
class BookListController(
    private val useCase: BookListUseCase
) {
    @Get
    fun getlist(): List<Book> = useCase()
}
