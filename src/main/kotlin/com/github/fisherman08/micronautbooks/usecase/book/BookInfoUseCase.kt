package com.github.fisherman08.micronautbooks.usecase.book

import com.github.fisherman08.micronautbooks.domain.book.Book
import com.github.fisherman08.micronautbooks.domain.book.BookId
import com.github.fisherman08.micronautbooks.domain.book.BookRepository
import io.micronaut.transaction.SynchronousTransactionManager
import java.sql.Connection
import javax.inject.Singleton

@Singleton
class BookInfoUseCase(
    private val bookRepository: BookRepository,
    private val transactionManager: SynchronousTransactionManager<Connection>
) {
    operator fun invoke(id: BookId): Book {
        return transactionManager.executeRead {
            bookRepository.find(id)
        }
    }
}
