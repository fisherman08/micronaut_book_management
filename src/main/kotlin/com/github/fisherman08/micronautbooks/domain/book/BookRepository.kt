package com.github.fisherman08.micronautbooks.domain.book

interface BookRepository {
    fun listAll(): List<Book>
    fun find(bookId: BookId): Book
    fun save(book :Book)
    fun delete(bookId: BookId)
}
