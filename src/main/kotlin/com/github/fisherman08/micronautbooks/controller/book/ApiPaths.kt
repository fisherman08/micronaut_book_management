package com.github.fisherman08.micronautbooks.controller.book

object ApiPaths {
    const val base = "/api"

    object Book {
        const val base = "${ApiPaths.base}/book"
        const val list = "${ApiPaths.Book.base}/list"
    }
}
