package com.github.fisherman08.micronautbooks.implementations.jooqimpl.table

import org.jooq.impl.DSL
import java.util.*

object BookTable {
    val table = DSL.table("book")

    val id = DSL.field("id", UUID::class.java)
    val title = DSL.field("title", String::class.java)
}

