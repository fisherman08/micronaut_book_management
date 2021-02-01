package com.github.fisherman08.micronautbooks.controller.writer

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import com.github.fisherman08.micronautbooks.TestUtils
import com.github.fisherman08.micronautbooks.controller.ApiPaths
import com.github.fisherman08.micronautbooks.domain.writer.WriterId
import com.github.fisherman08.micronautbooks.domain.writer.WriterRepository
import com.github.fisherman08.micronautbooks.domain.writer.WriterName
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import io.micronaut.http.HttpRequest
import io.micronaut.http.client.HttpClient
import io.micronaut.http.client.annotation.Client
import io.micronaut.runtime.server.EmbeddedServer
import io.micronaut.test.extensions.kotest.annotation.MicronautTest
import io.micronaut.transaction.SynchronousTransactionManager
import org.jooq.DSLContext
import java.sql.Connection
import java.util.*

@MicronautTest
class WriterUpdateControllerTest(
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

    "既存のWriterを更新できる" {
        val newName = "２代目三島由紀夫"
        val body = "{\"name\": \"$newName\"}"
        val response = client.toBlocking().retrieve(HttpRequest.POST(ApiPaths.Writer.update.replace("{id}", existingId.toString()), body))
        val responseData = jacksonObjectMapper().readValue<WriterUpdateController.ResponseBody>(response)

        val result = bookRepository.find(WriterId(responseData.id))
        result.id shouldBe WriterId(responseData.id)
        result.name shouldBe WriterName.fromString(responseData.name)
    }
})
