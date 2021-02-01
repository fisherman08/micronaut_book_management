package com.github.fisherman08.micronautbooks.implementations.jooqimpl

import com.github.fisherman08.micronautbooks.domain.book.*
import com.github.fisherman08.micronautbooks.domain.exception.NotFoundException
import com.github.fisherman08.micronautbooks.domain.writer.Writer
import com.github.fisherman08.micronautbooks.domain.writer.WriterId
import com.github.fisherman08.micronautbooks.domain.writer.WriterName
import com.github.fisherman08.micronautbooks.implementations.jooqimpl.table.BookAuthorTable
import com.github.fisherman08.micronautbooks.implementations.jooqimpl.table.BookTable
import com.github.fisherman08.micronautbooks.implementations.jooqimpl.table.WriterTable
import org.jooq.DSLContext
import org.jooq.Record2
import java.util.*
import javax.inject.Singleton

@Singleton
class JooqBookRepository(
    private val context: DSLContext
) : BookRepository {

    override fun list(authorIds: List<WriterId>): List<Book> {
        val query =
            context
                .select(
                    BookTable.id,
                    BookTable.title
                )
                .from(BookTable.table)

        if (authorIds.isNotEmpty()) {
            query.whereExists(
                context.select(BookAuthorTable.bookId)
                    .from(BookAuthorTable.table)
                    .where(BookAuthorTable.bookId.eq(BookTable.id)).and(BookAuthorTable.authorId.`in`(authorIds.map { it.value }))
            )
        }
        val result = query.orderBy(BookTable.title).fetch()
        val authors = getAuthors(result.map { it[BookTable.id] })

        return result.map { row ->
            extract(row, authors[row.getValue(BookTable.id)])
        }
    }

    override fun find(bookId: BookId): Book {
        val result =
            context.select(
                BookTable.id,
                BookTable.title
            )
                .from(BookTable.table)
                .where(BookTable.id.eq(bookId.value))
        val authors = getAuthors(result.map { it[BookTable.id] })
        return result.singleOrNull()?.let { extract(it, authors[it.getValue(BookTable.id)]) }
            ?: throw NotFoundException(Book::class)
    }

    override fun save(book: Book) {
        // book
        context
            .insertInto(BookTable.table)
            .columns(BookTable.id, BookTable.title)
            .values(book.id.value, book.title.value)
            .onDuplicateKeyUpdate()
            .set(BookTable.title, book.title.value)
            .execute()

        // book_authorはdelete & insertしておく
        context.deleteFrom(BookAuthorTable.table).where(BookAuthorTable.bookId.eq(book.id.value)).execute()
        book.authors.forEach { author ->
            context
                .insertInto(BookAuthorTable.table)
                .columns(BookAuthorTable.bookId, BookAuthorTable.authorId)
                .values(book.id.value, author.id.value)
                .execute()
        }
    }

    override fun delete(bookId: BookId) {
        context.deleteFrom(BookTable.table).where(BookTable.id.eq(bookId.value)).execute()
    }

    private fun getAuthors(ids: List<UUID>): Map<UUID, List<Writer>> {
        val result = context
            .select(
                BookAuthorTable.bookId,
                WriterTable.id,
                WriterTable.name
            )
            .from(BookAuthorTable.table)
            .innerJoin(WriterTable.table).on(BookAuthorTable.authorId.eq(WriterTable.id))
            .where(BookAuthorTable.bookId.`in`(ids))
            .orderBy(WriterTable.name).fetchGroups(BookAuthorTable.bookId)

        return ids.associateWith { id ->
            result[id]?.let { list ->
                list.map { record ->
                    Writer(
                        id = WriterId(record.getValue(WriterTable.id)),
                        name = WriterName.fromString(record.getValue(WriterTable.name))
                    )
                }
            } ?: emptyList()
        }
    }

    private fun extract(row: Record2<UUID, String>, authors: List<Writer>?): Book = Book(
        id = BookId(row.getValue(BookTable.id)),
        title = BookTitle(row.getValue(BookTable.title)),
        authors = Authors.fromCollection(authors ?: emptyList())
    )
}
