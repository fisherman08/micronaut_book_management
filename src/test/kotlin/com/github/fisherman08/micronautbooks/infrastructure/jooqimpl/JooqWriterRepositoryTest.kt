package com.github.fisherman08.micronautbooks.infrastructure.jooqimpl

import com.github.fisherman08.micronautbooks.TestUtils
import com.github.fisherman08.micronautbooks.domain.exception.NotFoundException
import com.github.fisherman08.micronautbooks.domain.writer.Writer
import com.github.fisherman08.micronautbooks.domain.writer.WriterId
import com.github.fisherman08.micronautbooks.domain.writer.WriterName
import com.github.fisherman08.micronautbooks.implementations.jooqimpl.JooqWriterRepository
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import io.micronaut.test.extensions.kotest.annotation.MicronautTest
import io.micronaut.transaction.SynchronousTransactionManager
import org.jooq.DSLContext
import java.sql.Connection
import java.util.*

@MicronautTest
class JooqWriterRepositoryTest(
    private val dslContext: DSLContext,
    private val transactionManager: SynchronousTransactionManager<Connection>
) : StringSpec({

    val repository = JooqWriterRepository(dslContext)

    beforeEach {
        TestUtils.cleaAllTables(dslContext, transactionManager)
    }

    "listAll: 全件取得できる" {
        TestUtils.insertWriter(dslContext, transactionManager, UUID.randomUUID(), "与沢翼")
        TestUtils.insertWriter(dslContext, transactionManager, UUID.randomUUID(), "夏目漱石")

        val result = repository.listAll()
        result.size shouldBe 2
    }

    "listByIds: 複数のID指定で取得できる" {
        val id1 = UUID.randomUUID()
        val id2 = UUID.randomUUID()
        val id3 = UUID.randomUUID()
        TestUtils.insertWriter(dslContext, transactionManager, id1, "aaa")
        TestUtils.insertWriter(dslContext, transactionManager, id2, "bbb")
        TestUtils.insertWriter(dslContext, transactionManager, id3, "ccc")

        val result = repository.listByIds(
            listOf(
                WriterId(id1),
                WriterId(id3)
            )
        )

        result.size shouldBe 2
        result[0].name.value shouldBe "aaa"
        result[1].name.value shouldBe "ccc"
    }

    "find: 存在するIDで1件取得できる" {
        val id = WriterId.generate()
        val name = WriterName.fromString("太宰治")
        TestUtils.insertWriter(dslContext, transactionManager, id.value, name.value)

        val result = repository.find(id)
        result.id shouldBe id
        result.name shouldBe name
    }

    "find: 存在しないIDで例外発生" {
        val id = WriterId.generate()
        val name = WriterName.fromString("太宰治")
        TestUtils.insertWriter(dslContext, transactionManager, id.value, name.value)

        shouldThrow<NotFoundException> {
            repository.find(WriterId.generate())
        }
    }

    "save: 新規登録できる" {
        val writer = Writer.register(
            name = WriterName.fromString("三島由紀夫")
        )
        repository.save(writer)

        val result = repository.find(writer.id)
        result shouldBe writer
    }

    "save: 既存のデータを更新できる" {
        val original = Writer.register(
            name = WriterName.fromString("三島由紀夫")
        )

        TestUtils.insertWriter(dslContext, transactionManager, original.id.value, original.name.value)

        val changedWriter = original.copy(name = WriterName.fromString("幸徳秋水"))
        repository.save(changedWriter)

        val result = repository.find(original.id)
        result shouldBe changedWriter
    }

    "delete: ID指定で削除できる" {
        val id = WriterId.generate()
        val name = WriterName.fromString("太宰治")
        TestUtils.insertWriter(dslContext, transactionManager, id.value, name.value)

        repository.delete(id)

        shouldThrow<NotFoundException> {
            repository.find(id)
        }
    }
})
