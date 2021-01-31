package com.github.fisherman08.micronautbooks.implementations.jooqimpl

import com.github.fisherman08.micronautbooks.domain.book.*
import com.github.fisherman08.micronautbooks.domain.exception.NotFoundException
import com.github.fisherman08.micronautbooks.implementations.jooqimpl.table.BookTable
import org.jooq.DSLContext
import org.jooq.Record2
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

        return result.map(::extract)
    }

    override fun find(bookId: BookId): Book {
        val result =
            context.select(
                BookTable.id,
                BookTable.title
            )
                .from(BookTable.table)
                .where(BookTable.id.eq(bookId.value))

        return result.singleOrNull()?.let(::extract) ?: throw NotFoundException(Book::class)
    }

    override fun save(book: Book) {
        context
            .insertInto(BookTable.table)
            .columns(BookTable.id, BookTable.title)
            .values(book.id.value, book.title.value)
            .onDuplicateKeyUpdate()
            .set(BookTable.title, book.title.value)
            .execute()
    }

    override fun delete(bookId: BookId) {
        context.deleteFrom(BookTable.table).where(BookTable.id.eq(bookId.value)).execute()
    }

    private fun extract(row: Record2<Any, Any>): Book = Book(
        id = BookId(UUID.fromString(row.getValue(BookTable.id).toString())),
        title = BookTitle(row.getValue(BookTable.title).toString()),
        authors = Authors.fromCollection(emptyList())
    )
}
