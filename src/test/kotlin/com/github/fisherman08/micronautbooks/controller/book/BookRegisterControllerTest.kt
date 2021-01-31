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
import io.micronaut.http.HttpRequestFactory
import io.micronaut.http.client.HttpClient
import io.micronaut.http.client.annotation.Client
import io.micronaut.runtime.server.EmbeddedServer
import io.micronaut.test.extensions.kotest.annotation.MicronautTest
import io.micronaut.transaction.SynchronousTransactionManager
import org.jooq.DSLContext
import java.net.http.HttpRequest
import java.sql.Connection
import java.util.*

@MicronautTest
class BookRegisterControllerTest(
    private val server: EmbeddedServer,
    @Client("/")
    private val client: HttpClient,
    private val dslContext: DSLContext,
    private val transactionManager: SynchronousTransactionManager<Connection>,
    private val bookRepository: BookRepository
) : StringSpec({

    TestUtils.cleaAllTables(dslContext, transactionManager)

    "登録できる" {
        val title = "登録テスト"
        val body = "{\"title\": \"$title\"}"
        val response = client.toBlocking().retrieve(io.micronaut.http.HttpRequest.POST(ApiPaths.Book.register, body))
        val responseData = jacksonObjectMapper().readValue<BookRegisterController.ResponseBody>(response)

        val result = bookRepository.find(BookId(responseData.id))
        result.id shouldBe BookId(responseData.id)
        result.title shouldBe BookTitle.fromString(responseData.title)
    }
})
