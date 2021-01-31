package com.github.fisherman08.micronautbooks.usecase.book

import com.github.fisherman08.micronautbooks.domain.book.Book
import com.github.fisherman08.micronautbooks.domain.book.BookRepository
import com.github.fisherman08.micronautbooks.domain.book.BookTitle
import io.micronaut.transaction.SynchronousTransactionManager
import java.sql.Connection
import javax.inject.Singleton

@Singleton
class BookRegisterUseCase(
    private val bookRepository: BookRepository,
    private val transactionManager: SynchronousTransactionManager<Connection>
) {
    operator fun invoke(title: BookTitle): Book {
        val book = Book.register(
            title = title,
            authors = emptyList()
        )
        transactionManager.executeWrite {
            bookRepository.save(book)
        }
        return book
    }
}
