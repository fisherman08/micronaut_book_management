package com.github.fisherman08.micronautbooks.domain.book

interface BookRepository {
    fun listAll(): List<Book>
}
