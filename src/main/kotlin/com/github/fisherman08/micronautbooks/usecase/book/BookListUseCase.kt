package com.github.fisherman08.micronautbooks.usecase.book

import com.github.fisherman08.micronautbooks.domain.book.Book
import javax.inject.Singleton

@Singleton
class BookListUseCase {
    // TODO: 動作確認のための仮実装
    operator fun invoke(): List<Book> = emptyList()
}
