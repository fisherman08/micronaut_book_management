package com.github.fisherman08.micronautbooks.usecase.book

import com.github.fisherman08.micronautbooks.domain.book.Book
import com.github.fisherman08.micronautbooks.domain.book.BookId
import com.github.fisherman08.micronautbooks.domain.book.BookRepository
import com.github.fisherman08.micronautbooks.domain.book.BookTitle
import io.micronaut.transaction.SynchronousTransactionManager
import java.sql.Connection
import javax.inject.Singleton

@Singleton
class BookUpdateUseCase(
    private val bookRepository: BookRepository,
    private val transactionManager: SynchronousTransactionManager<Connection>
) {
    operator fun invoke(id: BookId, title: BookTitle): Book {
        return transactionManager.executeWrite {
            val updated = bookRepository.find(id).copy(title = title)
            bookRepository.save(updated)
            updated
        }
    }
}
