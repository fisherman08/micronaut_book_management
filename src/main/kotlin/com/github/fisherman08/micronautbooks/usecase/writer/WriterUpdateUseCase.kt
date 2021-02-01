package com.github.fisherman08.micronautbooks.usecase.writer

import com.github.fisherman08.micronautbooks.domain.writer.Writer
import com.github.fisherman08.micronautbooks.domain.writer.WriterId
import com.github.fisherman08.micronautbooks.domain.writer.WriterName
import com.github.fisherman08.micronautbooks.domain.writer.WriterRepository
import io.micronaut.transaction.SynchronousTransactionManager
import java.sql.Connection
import javax.inject.Singleton

@Singleton
class WriterUpdateUseCase(
    private val writerRepository: WriterRepository,
    private val transactionManager: SynchronousTransactionManager<Connection>
) {
    operator fun invoke(id: WriterId, name: WriterName): Writer {
        return transactionManager.executeWrite {
            val updated = writerRepository.find(id).copy(name = name)
            writerRepository.save(updated)
            updated
        }
    }
}
