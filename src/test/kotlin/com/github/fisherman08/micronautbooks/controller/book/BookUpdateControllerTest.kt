package com.github.fisherman08.micronautbooks.controller.book

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import com.github.fisherman08.micronautbooks.TestUtils
import com.github.fisherman08.micronautbooks.controller.ApiPaths
import com.github.fisherman08.micronautbooks.domain.book.BookId
import com.github.fisherman08.micronautbooks.domain.book.BookRepository
import com.github.fisherman08.micronautbooks.domain.book.BookTitle
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
class BookUpdateControllerTest(
    private val server: EmbeddedServer,
    @Client("/")
    private val client: HttpClient,
    private val dslContext: DSLContext,
    private val transactionManager: SynchronousTransactionManager<Connection>,
    private val bookRepository: BookRepository
) : StringSpec({

    TestUtils.cleaAllTables(dslContext, transactionManager)
    val existingId = UUID.randomUUID()
    TestUtils.insertBook(dslContext, transactionManager, existingId, "学問のすすめ")

    "既存のbookを更新できる" {
        val newTitle = "学問のすすめ2"
        val body = "{\"title\": \"$newTitle\"}"
        val response = client.toBlocking().retrieve(HttpRequest.POST("${ApiPaths.Book.update}/$existingId", body))
        val responseData = jacksonObjectMapper().readValue<BookUpdateController.ResponseBody>(response)

        val result = bookRepository.find(BookId(responseData.id))
        result.id shouldBe BookId(responseData.id)
        result.title shouldBe BookTitle.fromString(responseData.title)
    }
})
