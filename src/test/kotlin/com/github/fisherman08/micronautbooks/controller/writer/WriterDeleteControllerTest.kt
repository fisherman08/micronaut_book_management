package com.github.fisherman08.micronautbooks.controller.writer

import com.github.fisherman08.micronautbooks.TestUtils
import com.github.fisherman08.micronautbooks.controller.ApiPaths
import com.github.fisherman08.micronautbooks.domain.writer.WriterId
import com.github.fisherman08.micronautbooks.domain.writer.WriterRepository
import com.github.fisherman08.micronautbooks.domain.exception.NotFoundException
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.StringSpec
import io.micronaut.http.HttpRequest
import io.micronaut.http.client.HttpClient
import io.micronaut.http.client.annotation.Client
import io.micronaut.http.client.exceptions.HttpClientResponseException
import io.micronaut.runtime.server.EmbeddedServer
import io.micronaut.test.extensions.kotest.annotation.MicronautTest
import io.micronaut.transaction.SynchronousTransactionManager
import org.jooq.DSLContext
import java.sql.Connection
import java.util.*

@MicronautTest
class WriterDeleteControllerTest(
    private val server: EmbeddedServer,
    @Client("/")
    private val client: HttpClient,
    private val dslContext: DSLContext,
    private val transactionManager: SynchronousTransactionManager<Connection>,
    private val bookRepository: WriterRepository
) : StringSpec({

    TestUtils.cleaAllTables(dslContext, transactionManager)
    val existingId = UUID.randomUUID()
    TestUtils.insertWriter(dslContext, transactionManager, existingId, "三島由紀夫")

    "既存のwriterを削除できる" {

        shouldThrow<HttpClientResponseException> {
            // 204レスポンスに対してclientは null body という例外を発生させる
            client.toBlocking().retrieve(
                HttpRequest.DELETE(ApiPaths.Writer.delete.replace("{id}", existingId.toString()), null)
            )
        }

        shouldThrow<NotFoundException> {
            bookRepository.find(WriterId(existingId))
        }
    }
})
