package com.github.fisherman08.micronautbooks.usecase.writer

import com.github.fisherman08.micronautbooks.domain.writer.Writer
import com.github.fisherman08.micronautbooks.domain.writer.WriterName
import com.github.fisherman08.micronautbooks.domain.writer.WriterRepository
import io.micronaut.transaction.SynchronousTransactionManager
import java.sql.Connection
import javax.inject.Singleton

@Singleton
class WriterRegisterUseCase(
    private val writerRepository: WriterRepository,
    private val transactionManager: SynchronousTransactionManager<Connection>
) {
    operator fun invoke(name: WriterName): Writer {
        val book = Writer.register(
            name = name
        )
        transactionManager.executeWrite {
            writerRepository.save(book)
        }
        return book
    }
}
