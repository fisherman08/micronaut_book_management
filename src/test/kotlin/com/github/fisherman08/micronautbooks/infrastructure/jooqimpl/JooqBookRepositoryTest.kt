package com.github.fisherman08.micronautbooks.infrastructure.jooqimpl

import com.github.fisherman08.micronautbooks.TestUtils
import com.github.fisherman08.micronautbooks.implementations.jooqimpl.JooqBookRepository
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import io.micronaut.test.extensions.kotest.annotation.MicronautTest
import io.micronaut.transaction.SynchronousTransactionManager
import org.jooq.DSLContext
import java.sql.Connection
import java.util.*

@MicronautTest
class JooqBookRepositoryTest(
    private val dslContext: DSLContext,
    private val transactionManager: SynchronousTransactionManager<Connection>
) : StringSpec({

    val repository = JooqBookRepository(dslContext)

    beforeEach {
        TestUtils.cleaAllTables(dslContext, transactionManager)
    }

    "listAll: 前件取得できる" {
        TestUtils.insertBook(dslContext, transactionManager, UUID.randomUUID(), "吾輩は猫である")
        TestUtils.insertBook(dslContext, transactionManager, UUID.randomUUID(), "学問のすすめ")

        val result = repository.listAll()
        result.size shouldBe 2
    }
})
