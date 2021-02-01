package com.github.fisherman08.micronautbooks.controller.book

import com.github.fisherman08.micronautbooks.TestUtils
import com.github.fisherman08.micronautbooks.controller.ApiPaths
import com.github.fisherman08.micronautbooks.domain.book.Book
import com.github.fisherman08.micronautbooks.domain.book.BookId
import com.github.fisherman08.micronautbooks.domain.book.BookRepository
import com.github.fisherman08.micronautbooks.domain.book.BookTitle
import com.github.fisherman08.micronautbooks.domain.exception.NotFoundException
import com.github.fisherman08.micronautbooks.domain.writer.Writer
import com.github.fisherman08.micronautbooks.domain.writer.WriterName
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
class BookDeleteControllerTest(
    private val server: EmbeddedServer,
    @Client("/")
    private val client: HttpClient,
    private val dslContext: DSLContext,
    private val transactionManager: SynchronousTransactionManager<Connection>,
    private val bookRepository: BookRepository
) : StringSpec({

    val book1 = Book.register(
        title = BookTitle.fromString("吾輩は猫である"),
        authors = listOf(
            Writer.register(
                name = WriterName.fromString("a夏目漱石")
            ),
            Writer.register(
                name = WriterName.fromString("b夏目漱石2世")
            )
        )
    )
    TestUtils.cleaAllTables(dslContext, transactionManager)
    TestUtils.insertBook(dslContext, transactionManager, book1)

    "既存のbookを削除できる" {

        shouldThrow<HttpClientResponseException> {
            // 204レスポンスに対してclientは null body という例外を発生させる
            client.toBlocking().retrieve(
                HttpRequest.DELETE(ApiPaths.Book.delete.replace("{id}", book1.id.value.toString()), null)
            )
        }

        shouldThrow<NotFoundException> {
            bookRepository.find(book1.id)
        }
    }
})
