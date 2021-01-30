package com.github.fisherman08.micronautbooks.domain.book

data class BookTitle(val value: String) {
    companion object {
        fun fromString(value: String): BookTitle = BookTitle(value = value)
    }
}
