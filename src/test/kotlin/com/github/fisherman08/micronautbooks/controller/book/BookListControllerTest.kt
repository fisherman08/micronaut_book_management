package com.github.fisherman08.micronautbooks.controller.book

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import com.github.fisherman08.micronautbooks.TestUtils
import com.github.fisherman08.micronautbooks.controller.ApiPaths
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

    val id1 = UUID.randomUUID()
    val title1 = "吾輩は猫である"
    val id2 = UUID.randomUUID()
    val title2 = "学問のすすめ"

    TestUtils.insertBook(dslContext, transactionManager, id1, title1)
    TestUtils.insertBook(dslContext, transactionManager, id2, title2)

    "全件取得される" {

        val expected = listOf(
            BookListController.ResponseBody(
                id = id1,
                title = title1
            ),
            BookListController.ResponseBody(
                id = id2,
                title = title2
            )
        )

        val response = client.toBlocking().retrieve(ApiPaths.Book.list)
        jacksonObjectMapper().readValue<List<BookListController.ResponseBody>>(response) shouldBe expected
    }
})
