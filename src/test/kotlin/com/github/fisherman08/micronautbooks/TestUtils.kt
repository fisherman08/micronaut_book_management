package com.github.fisherman08.micronautbooks

import com.github.fisherman08.micronautbooks.domain.book.Book
import com.github.fisherman08.micronautbooks.domain.writer.Writer
import io.micronaut.transaction.SynchronousTransactionManager
import org.jooq.DSLContext
import org.jooq.impl.DSL
import java.sql.Connection
import java.util.*

object TestUtils {

    fun cleaAllTables(context: DSLContext, transactionManager: SynchronousTransactionManager<Connection>) {
        clearBookAuthor(context, transactionManager)
        clearBook(context, transactionManager)
        clearWriter(context, transactionManager)
    }

    fun clearBookAuthor(context: DSLContext, transactionManager: SynchronousTransactionManager<Connection>) = clear(context, transactionManager, "book_author")

    fun clearBook(context: DSLContext, transactionManager: SynchronousTransactionManager<Connection>) = clear(context, transactionManager, "book")

    fun clearWriter(context: DSLContext, transactionManager: SynchronousTransactionManager<Connection>) = clear(context, transactionManager, "writer")

    fun insertBook(context: DSLContext, transactionManager: SynchronousTransactionManager<Connection>, book: Book) {
        insertBook(context, transactionManager, book.id.value, book.title.value, book.authors.list)
    }

    fun insertBook(context: DSLContext, transactionManager: SynchronousTransactionManager<Connection>, id: UUID, title: String, authors: List<Writer> = emptyList()) {
        transactionManager.executeWrite {
            val query = context.insertInto(DSL.table("book"))
                .columns(DSL.field("id"), DSL.field("title"))
                .values(DSL.value(id), DSL.value(title))
            query.execute()

            authors.forEach { author ->
                insertWriter(context, transactionManager, author)
                insertBookAuthor(context, transactionManager, id, author.id.value)
            }


        }
    }

    fun insertWriter(context: DSLContext, transactionManager: SynchronousTransactionManager<Connection>, writer: Writer) {
        insertWriter(context, transactionManager, writer.id.value, writer.name.value)
    }

    fun insertWriter(context: DSLContext, transactionManager: SynchronousTransactionManager<Connection>, id: UUID, name: String) {
        transactionManager.executeWrite {
            val query = context.insertInto(DSL.table("writer"))
                .columns(DSL.field("id"), DSL.field("name"))
                .values(DSL.value(id), DSL.value(name))
            query.execute()
        }
    }

    fun insertBookAuthor(context: DSLContext, transactionManager: SynchronousTransactionManager<Connection>, bookId: UUID, authorID: UUID) {
        transactionManager.executeWrite {
            val query = context.insertInto(DSL.table("book_author"))
                .columns(DSL.field("book_id"), DSL.field("author_id"))
                .values(DSL.value(bookId), DSL.value(authorID))
            query.execute()
        }
    }

    fun clear(context: DSLContext, transactionManager: SynchronousTransactionManager<Connection>, tableName: String) {
        transactionManager.executeWrite {
            val query = context.deleteFrom(DSL.table(tableName))
            query.execute()
        }
    }

}
