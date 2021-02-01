package com.github.fisherman08.micronautbooks.domain.writer

import java.util.*

data class WriterId(val value: UUID) {
    companion object {
        fun generate(): WriterId = WriterId(UUID.randomUUID())

        fun fromString(string: String): WriterId = WriterId(UUID.fromString(string))
    }
}
