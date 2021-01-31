package com.github.fisherman08.micronautbooks.implementations.jooqimpl.table

import org.jooq.impl.DSL

object WriterTable {
    val table = DSL.table("writer")

    val id = DSL.field("id")
    val name = DSL.field("name")
}

