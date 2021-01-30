package com.github.fisherman08.micronautbooks.controller.book

import com.github.fisherman08.micronautbooks.controller.ApiPaths
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get

@Controller(ApiPaths.Book.list)
class BookListController {
    @Get
    fun getlist(): List<String> = listOf("aaa", "bbb", "ccc")
}
