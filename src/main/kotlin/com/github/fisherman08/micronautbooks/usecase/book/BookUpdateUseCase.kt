package com.github.fisherman08.micronautbooks.usecase.book

import com.github.fisherman08.micronautbooks.domain.book.*
import com.github.fisherman08.micronautbooks.domain.writer.WriterId
import com.github.fisherman08.micronautbooks.domain.writer.WriterRepository
import io.micronaut.transaction.SynchronousTransactionManager
import java.sql.Connection
import javax.inject.Singleton

@Singleton
class BookUpdateUseCase(
    private val bookRepository: BookRepository,
    private val writerRepository: WriterRepository,
    private val transactionManager: SynchronousTransactionManager<Connection>
) {
    operator fun invoke(id: BookId, title: BookTitle, authorIds: List<WriterId>): Book {
        return transactionManager.executeWrite {
            //TODO: 渡された著者IDのvalidationは省略
            val authors = writerRepository.listByIds(authorIds)
            val updated = bookRepository.find(id).copy(title = title, authors = Authors.fromCollection(authors))
            bookRepository.save(updated)
            updated
        }
    }
}
