package com.github.fisherman08.micronautbooks.usecase.book

import com.github.fisherman08.micronautbooks.domain.book.Book
import com.github.fisherman08.micronautbooks.domain.book.BookRepository
import com.github.fisherman08.micronautbooks.domain.book.BookTitle
import com.github.fisherman08.micronautbooks.domain.writer.WriterId
import com.github.fisherman08.micronautbooks.domain.writer.WriterRepository
import io.micronaut.transaction.SynchronousTransactionManager
import java.sql.Connection
import javax.inject.Singleton

@Singleton
class BookRegisterUseCase(
    private val bookRepository: BookRepository,
    private val writerRepository: WriterRepository,
    private val transactionManager: SynchronousTransactionManager<Connection>
) {
    operator fun invoke(title: BookTitle, authorIds: List<WriterId>): Book =

        transactionManager.executeWrite {
            //TODO: 渡された著者IDのvalidationは省略
            val authors = writerRepository.listByIds(authorIds)
            val book = Book.register(
                title = title,
                authors = authors
            )
            bookRepository.save(book)
            book
        }

}
