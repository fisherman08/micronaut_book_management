package com.github.fisherman08.micronautbooks.domain.book

import com.github.fisherman08.micronautbooks.domain.writer.Writer

data class Book(
    val id: BookId,
    val title: BookTitle,
    val authors: Authors
) {
    companion object {
        fun register(title: String, authors: Collection<Writer>): Book =
            Book(
                id = BookId.generate(),
                title = BookTitle.fromString(value = title),
                authors = Authors.fromCollection(collection = authors)
            )
    }
}
