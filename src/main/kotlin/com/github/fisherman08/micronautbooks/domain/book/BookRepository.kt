package com.github.fisherman08.micronautbooks.domain.book

import com.github.fisherman08.micronautbooks.domain.writer.WriterId

interface BookRepository {
    fun list(authorIds: List<WriterId>): List<Book>
    fun find(bookId: BookId): Book
    fun save(book: Book)
    fun delete(bookId: BookId)
}
