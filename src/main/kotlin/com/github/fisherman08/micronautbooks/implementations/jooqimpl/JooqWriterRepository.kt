package com.github.fisherman08.micronautbooks.implementations.jooqimpl

import com.github.fisherman08.micronautbooks.domain.exception.NotFoundException
import com.github.fisherman08.micronautbooks.domain.writer.Writer
import com.github.fisherman08.micronautbooks.domain.writer.WriterId
import com.github.fisherman08.micronautbooks.domain.writer.WriterRepository
import com.github.fisherman08.micronautbooks.implementations.jooqimpl.table.WriterTable
import org.jooq.DSLContext
import javax.inject.Singleton

@Singleton
class JooqWriterRepository(
    private val dslContext: DSLContext
) : WriterRepository {
    override fun listAll(): List<Writer> {
        val result = dslContext
            .select(WriterTable.id, WriterTable.name)
            .from(WriterTable.table)
            .orderBy(WriterTable.name).fetch()

        return result.map(WriterTable::extract)
    }

    override fun listByIds(ids: List<WriterId>): List<Writer> {
        val result = dslContext
            .select(WriterTable.id, WriterTable.name)
            .from(WriterTable.table)
            .where(WriterTable.id.`in`(ids.map { it.value }))
            .orderBy(WriterTable.name).fetch()

        return result.map(WriterTable::extract)
    }

    override fun find(writerId: WriterId): Writer {
        val result = dslContext
            .select(WriterTable.id, WriterTable.name)
            .from(WriterTable.table)
            .where(WriterTable.id.eq(writerId.value)).fetch()
        return result.singleOrNull()?.let(WriterTable::extract) ?: throw NotFoundException(Writer::class)
    }

    override fun save(writer: Writer) {
        dslContext
            .insertInto(WriterTable.table)
            .columns(WriterTable.id, WriterTable.name)
            .values(writer.id.value, writer.name.value)
            .onDuplicateKeyUpdate()
            .set(WriterTable.name, writer.name.value)
            .execute()
    }

    override fun delete(writerId: WriterId) {
        dslContext.deleteFrom(WriterTable.table).where(WriterTable.id.eq(writerId.value)).execute()
    }

}
