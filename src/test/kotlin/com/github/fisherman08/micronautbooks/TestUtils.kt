package com.github.fisherman08.micronautbooks

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

    fun insertBook(context: DSLContext, transactionManager: SynchronousTransactionManager<Connection>, id: UUID, title: String) {
        transactionManager.executeWrite {
            val query = context.insertInto(DSL.table("book"))
                .columns(DSL.field("id"), DSL.field("title"))
                .values(DSL.value(id), DSL.value(title))
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
