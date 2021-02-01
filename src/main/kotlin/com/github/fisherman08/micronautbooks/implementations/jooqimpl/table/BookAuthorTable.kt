package com.github.fisherman08.micronautbooks.implementations.jooqimpl.table

import org.jooq.impl.DSL
import java.util.*

object BookAuthorTable {
    val table = DSL.table("book_author")

    val bookId = DSL.field("book_id", UUID::class.java)
    val authorId = DSL.field("author_id", UUID::class.java)
}

