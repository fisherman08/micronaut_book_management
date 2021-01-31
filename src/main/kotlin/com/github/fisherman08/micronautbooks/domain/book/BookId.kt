package com.github.fisherman08.micronautbooks.domain.book

import java.util.*

data class BookId(val value: UUID) {
    companion object {
        fun generate(): BookId = BookId(value = UUID.randomUUID())

        fun fromString(string: String) = BookId(UUID.fromString(string))
    }
}
