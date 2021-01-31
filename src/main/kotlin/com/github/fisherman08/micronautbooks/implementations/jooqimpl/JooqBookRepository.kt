package com.github.fisherman08.micronautbooks.implementations.jooqimpl

import com.github.fisherman08.micronautbooks.domain.book.*
import com.github.fisherman08.micronautbooks.implementations.jooqimpl.table.BookTable
import org.jooq.DSLContext
import java.util.*
import javax.inject.Singleton

@Singleton
class JooqBookRepository(
    private val context: DSLContext
) : BookRepository {
    override fun listAll(): List<Book> {
        val result =
            context
                .select(
                    BookTable.id,
                    BookTable.title
                )
                .from(BookTable.table)
                .orderBy(BookTable.title).fetch()

        return result.map { row ->
            Book(
                id = BookId(UUID.fromString(row.getValue(BookTable.id).toString())),
                title = BookTitle(row.getValue(BookTable.title).toString()),
                authors = Authors.fromCollection(emptyList())
            )
        }
    }
}
