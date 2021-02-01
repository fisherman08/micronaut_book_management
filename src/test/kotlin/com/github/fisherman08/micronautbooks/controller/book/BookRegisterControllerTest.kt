package com.github.fisherman08.micronautbooks.controller.book

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import com.github.fisherman08.micronautbooks.TestUtils
import com.github.fisherman08.micronautbooks.controller.ApiPaths
import com.github.fisherman08.micronautbooks.domain.book.Book
import com.github.fisherman08.micronautbooks.domain.book.BookId
import com.github.fisherman08.micronautbooks.domain.book.BookRepository
import com.github.fisherman08.micronautbooks.domain.book.BookTitle
import com.github.fisherman08.micronautbooks.domain.writer.Writer
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

    val authors = listOf(
        Writer.register(
            name = WriterName.fromString("a夏目漱石")
        ),
        Writer.register(
            name = WriterName.fromString("b夏目漱石2世")
        )
    )

    authors.forEach {
        TestUtils.insertWriter(dslContext, transactionManager, it)
    }

    "登録できる" {
        val body = "{\"title\": \"吾輩は猫である\", \"authorIds\": [${authors.joinToString(",") { "\"${it.id.value}\"" }}]}"
        val response = client.toBlocking().retrieve(HttpRequest.POST(ApiPaths.Book.register, body))
        val responseData = jacksonObjectMapper().readValue<BookRegisterController.ResponseBody>(response)

        val result = bookRepository.find(BookId(responseData.id))
        result.id shouldBe BookId(responseData.id)
        result.title shouldBe BookTitle.fromString(responseData.title)
        result.authors.size shouldBe 2
    }
})
