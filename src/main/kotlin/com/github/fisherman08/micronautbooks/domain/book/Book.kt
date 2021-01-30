package com.github.fisherman08.micronautbooks.domain.book

data class Book(
    val id: BookId,
    val title: BookTitle,
    val authors: Authors
)
