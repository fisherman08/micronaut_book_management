package com.github.fisherman08.micronautbooks.implementations.jooqimpl.table

import com.github.fisherman08.micronautbooks.domain.writer.Writer
import com.github.fisherman08.micronautbooks.domain.writer.WriterId
import com.github.fisherman08.micronautbooks.domain.writer.WriterName
import org.jooq.Record2
import org.jooq.impl.DSL
import java.util.*

object WriterTable {
    val table = DSL.table("writer")

    val id = DSL.field("id", UUID::class.java)
    val name = DSL.field("name", String::class.java)

    fun extract(row: Record2<UUID, String>): Writer =
        Writer(
            id = WriterId(row.get(id)),
            name = WriterName.fromString(row.get(name))
        )
}

