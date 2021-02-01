package com.github.fisherman08.micronautbooks.controller.writer

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import com.github.fisherman08.micronautbooks.TestUtils
import com.github.fisherman08.micronautbooks.controller.ApiPaths
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
class WriterInfoControllerTest(
    private val server: EmbeddedServer,
    @Client("/")
    private val client: HttpClient,
    private val dslContext: DSLContext,
    private val transactionManager: SynchronousTransactionManager<Connection>
) : StringSpec({

    TestUtils.cleaAllTables(dslContext, transactionManager)
    val existingId = UUID.randomUUID()
    val existingName = "太宰治"
    TestUtils.insertWriter(dslContext, transactionManager, existingId, existingName)

    "指定されたIDのWriter情報を取得できる" {
        val response = client.toBlocking().retrieve(ApiPaths.Writer.info.replace("{id}", existingId.toString()))
        val responseData = jacksonObjectMapper().readValue<WriterInfoController.ResponseBody>(response)

        responseData.id shouldBe existingId
        responseData.name shouldBe existingName
    }

    "存在しないIDの指定で404" {

        val response = shouldThrow<HttpClientResponseException> {
            // 404ならHttpClientResponseExceptionになる
            client.toBlocking().exchange<HttpResponse<Any>>(ApiPaths.Writer.info.replace("{id}", UUID.randomUUID().toString()))
        }

        response.status shouldBe HttpStatus.NOT_FOUND
    }
})
