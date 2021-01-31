package com.github.fisherman08.micronautbooks.implementations.jooqimpl.table

import org.jooq.impl.DSL

object BookTable {
    val table = DSL.table("book")

    val id = DSL.field("id")
    val title = DSL.field("title")
}

