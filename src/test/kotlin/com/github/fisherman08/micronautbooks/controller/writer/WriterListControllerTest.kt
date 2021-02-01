package com.github.fisherman08.micronautbooks.controller.writer

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
class WriterListControllerTest(
    private val server: EmbeddedServer,
    @Client("/")
    private val client: HttpClient,
    private val dslContext: DSLContext,
    private val transactionManager: SynchronousTransactionManager<Connection>
) : StringSpec({

    TestUtils.cleaAllTables(dslContext, transactionManager)

    val id1 = UUID.randomUUID()
    val name = "aaa"
    val id2 = UUID.randomUUID()
    val name2 = "bbb"

    TestUtils.insertWriter(dslContext, transactionManager, id1, name)
    TestUtils.insertWriter(dslContext, transactionManager, id2, name2)

    "全件取得される" {

        val expected = listOf(
            WriterListController.ResponseBody(
                id = id1,
                name = name
            ),
            WriterListController.ResponseBody(
                id = id2,
                name = name2
            )
        )

        val response = client.toBlocking().retrieve(ApiPaths.Writer.list)
        jacksonObjectMapper().readValue<List<WriterListController.ResponseBody>>(response) shouldBe expected
    }
})
