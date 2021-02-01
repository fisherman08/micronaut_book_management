package com.github.fisherman08.micronautbooks.usecase.writer

import com.github.fisherman08.micronautbooks.domain.writer.WriterId
import com.github.fisherman08.micronautbooks.domain.writer.WriterRepository
import io.micronaut.transaction.SynchronousTransactionManager
import java.sql.Connection
import javax.inject.Singleton

@Singleton
class WriterDeleteUseCase(
    private val writerRepository: WriterRepository,
    private val transactionManager: SynchronousTransactionManager<Connection>
) {
    operator fun invoke(id: WriterId) {
        transactionManager.executeWrite {
            writerRepository.delete(id)
        }
    }
}
