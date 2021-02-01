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
class BookUpdateControllerTest(
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

    val newAuthor = Writer.register(
        name = WriterName.fromString("c夏目漱石3世")
    )

    TestUtils.insertBook(dslContext, transactionManager, book1)
    TestUtils.insertWriter(dslContext, transactionManager, newAuthor)

    "既存のbookを更新できる" {
        val newTitle = "学問のすすめ2"
        val body = "{\"title\": \"$newTitle\", \"authorIds\": [\"${newAuthor.id.value}\"]}"
        val response = client.toBlocking().retrieve(HttpRequest.POST(ApiPaths.Book.update.replace("{id}", book1.id.value.toString()), body))
        val responseData = jacksonObjectMapper().readValue<BookUpdateController.ResponseBody>(response)

        val result = bookRepository.find(BookId(responseData.id))
        result.id shouldBe BookId(responseData.id)
        result.title shouldBe BookTitle.fromString(responseData.title)
        result.authors.first().id shouldBe newAuthor.id
    }
})
