package com.github.fisherman08.micronautbooks.controller.book

import com.github.fisherman08.micronautbooks.controller.ApiPaths
import com.github.fisherman08.micronautbooks.domain.book.Book
import com.github.fisherman08.micronautbooks.usecase.book.BookRegisterUseCase
import io.micronaut.http.annotation.Body
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.Post
import java.util.*

@Controller(ApiPaths.Book.register)
class BookRegisterController(
    private val register: BookRegisterUseCase
) {
    @Post
    fun registerBook(@Body body: RequestBody): ResponseBody {
        val book = register(body.title)
        return ResponseBody(id = book.id.value, title = book.title.value)
    }

    data class RequestBody(val title: String)
    data class ResponseBody(val id: UUID, val title: String)
}
