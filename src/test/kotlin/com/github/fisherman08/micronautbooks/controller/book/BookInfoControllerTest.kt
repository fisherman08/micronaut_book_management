package com.github.fisherman08.micronautbooks.controller.book

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import com.github.fisherman08.micronautbooks.TestUtils
import com.github.fisherman08.micronautbooks.controller.ApiPaths
import com.github.fisherman08.micronautbooks.domain.book.BookId
import com.github.fisherman08.micronautbooks.domain.book.BookTitle
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import io.micronaut.http.HttpStatus
import io.micronaut.http.client.HttpClient
import io.micronaut.http.client.annotation.Client
import io.micronaut.http.client.exceptions.HttpClientResponseException
import io.micronaut.runtime.server.EmbeddedServer
import io.micronaut.test.extensions.kotest.annotation.MicronautTest
import io.micronaut.transaction.SynchronousTransactionManager
import org.jooq.DSLContext
import java.net.http.HttpResponse
import java.sql.Connection
import java.util.*

@MicronautTest
class BookInfoControllerTest(
    private val server: EmbeddedServer,
    @Client("/")
    private val client: HttpClient,
    private val dslContext: DSLContext,
    private val transactionManager: SynchronousTransactionManager<Connection>
) : StringSpec({

    TestUtils.cleaAllTables(dslContext, transactionManager)
    val existingId = UUID.randomUUID()
    val existingTitle = "学問のすすめ"
    TestUtils.insertBook(dslContext, transactionManager, existingId, existingTitle)

    "指定されたIDのBook情報を取得できる" {
        val response = client.toBlocking().retrieve(ApiPaths.Book.info.replace("{id}", existingId.toString()))

        val responseData = jacksonObjectMapper().readValue<BookInfoController.ResponseBody>(response)

        responseData.id shouldBe existingId
        responseData.title shouldBe existingTitle
    }

    "存在しないIDの指定で404" {

        val response = shouldThrow<HttpClientResponseException> {
            // 404ならHttpClientResponseExceptionになる
            client.toBlocking().exchange<HttpResponse<Any>>(ApiPaths.Book.info.replace("{id}", UUID.randomUUID().toString()))
        }

        response.status shouldBe HttpStatus.NOT_FOUND
    }
})
