package com.github.fisherman08.micronautbooks.controller.book

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import com.github.fisherman08.micronautbooks.TestUtils
import com.github.fisherman08.micronautbooks.controller.ApiPaths
import com.github.fisherman08.micronautbooks.domain.book.Book
import com.github.fisherman08.micronautbooks.domain.book.BookTitle
import com.github.fisherman08.micronautbooks.domain.writer.Writer
import com.github.fisherman08.micronautbooks.domain.writer.WriterName
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import io.micronaut.http.client.HttpClient
import io.micronaut.http.client.annotation.Client
import io.micronaut.runtime.server.EmbeddedServer
import io.micronaut.test.extensions.kotest.annotation.MicronautTest
import io.micronaut.transaction.SynchronousTransactionManager
import org.jooq.DSLContext
import java.sql.Connection
import java.util.*

@MicronautTest
class BookListControllerTest(
    private val server: EmbeddedServer,
    @Client("/")
    private val client: HttpClient,
    private val dslContext: DSLContext,
    private val transactionManager: SynchronousTransactionManager<Connection>
) : StringSpec({

    TestUtils.cleaAllTables(dslContext, transactionManager)

    val book1 = Book.register(
        title = BookTitle.fromString("a吾輩は猫である"),
        authors = listOf(
            Writer.register(
                name = WriterName.fromString("a夏目漱石")
            ),
            Writer.register(
                name = WriterName.fromString("b夏目漱石2世")
            )
        )
    )

    val book2 = Book.register(
        title = BookTitle.fromString("b学問のすすめ"),
        authors = listOf(
            Writer.register(
                name = WriterName.fromString("清少納言")
            )
        )
    )
    TestUtils.insertBook(dslContext, transactionManager, book1)
    TestUtils.insertBook(dslContext, transactionManager, book2)

    "全件取得される" {

        val expected = listOf(
            BookListController.ResponseBody(
                id = book1.id.value,
                title = book1.title.value,
                authors = book1.authors.map { au -> BookListController.ResponseBody.Author(id = au.id.value, name = au.name.value)}
            ),
            BookListController.ResponseBody(
                id = book2.id.value,
                title = book2.title.value,
                authors = book2.authors.map { au -> BookListController.ResponseBody.Author(id = au.id.value, name = au.name.value)}
            )
        )

        val response = client.toBlocking().retrieve(ApiPaths.Book.list)
        jacksonObjectMapper().readValue<List<BookListController.ResponseBody>>(response) shouldBe expected
    }

    "著者指定でその著者の書籍のみ取得される" {

        val expected = listOf(
            BookListController.ResponseBody(
                id = book1.id.value,
                title = book1.title.value,
                authors = book1.authors.map { au -> BookListController.ResponseBody.Author(id = au.id.value, name = au.name.value)}
            )
        )

        val response = client.toBlocking().retrieve("${ApiPaths.Book.list}?authorIds=${book1.authors.map { it.id.value }.joinToString(",")}")
        jacksonObjectMapper().readValue<List<BookListController.ResponseBody>>(response) shouldBe expected
    }
})
