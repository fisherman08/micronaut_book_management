package com.github.fisherman08.micronautbooks.infrastructure.jooqimpl

import com.github.fisherman08.micronautbooks.TestUtils
import com.github.fisherman08.micronautbooks.domain.book.Book
import com.github.fisherman08.micronautbooks.domain.book.BookId
import com.github.fisherman08.micronautbooks.domain.book.BookTitle
import com.github.fisherman08.micronautbooks.domain.exception.NotFoundException
import com.github.fisherman08.micronautbooks.domain.writer.Writer
import com.github.fisherman08.micronautbooks.domain.writer.WriterName
import com.github.fisherman08.micronautbooks.implementations.jooqimpl.JooqBookRepository
import io.kotest.assertions.throwables.shouldThrow
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

    val book2 = Book.register(
        title = BookTitle.fromString("学問のすすめ"),
        authors = listOf(
            Writer.register(
                name = WriterName.fromString("清少納言")
            )
        )
    )

    beforeEach {
        TestUtils.cleaAllTables(dslContext, transactionManager)
    }

    "listAll: 前件取得できる" {
        TestUtils.insertBook(dslContext, transactionManager, book1)
        TestUtils.insertBook(dslContext, transactionManager, book2)

        val result = repository.listAll()
        result.size shouldBe 2
        result shouldBe listOf(book1, book2)
    }

    "find: 存在するIDで1件取得できる" {
        TestUtils.insertBook(dslContext, transactionManager, book1)

        val result = repository.find(book1.id)
        result shouldBe book1
    }

    "find: 存在しないIDで例外発生" {
        val id = BookId.generate()
        val title = BookTitle.fromString("吾輩は猫である")
        TestUtils.insertBook(dslContext, transactionManager, id.value, title.value)

        shouldThrow<NotFoundException> {
            repository.find(BookId.generate())
        }
    }

    "save: 新規登録できる" {
        val book = Book.register(
            title = BookTitle.fromString("人間失格"),
            authors = emptyList()
        )
        repository.save(book)

        val result = repository.find(book.id)
        result shouldBe book
    }

    "save: 既存のデータを更新できる" {
        val original = Book.register(
            title = BookTitle.fromString("人間失格"),
            authors = emptyList()
        )
        TestUtils.insertBook(dslContext, transactionManager, original.id.value, original.title.value)

        val changedBook = original.copy(title = BookTitle.fromString("人間失格2"))
        repository.save(changedBook)

        val result = repository.find(original.id)
        result shouldBe changedBook
    }

    "delete: ID指定で削除できる" {
        val id = BookId.generate()
        TestUtils.insertBook(dslContext, transactionManager, id.value, "Perfect 削除")

        repository.delete(id)

        shouldThrow<NotFoundException> {
            repository.find(id)
        }
    }
})
