package com.github.fisherman08.micronautbooks.domain.writer

data class Writer(
    val id: WriterId,
    val name: WriterName
) {
    companion object {
        fun register(name: WriterName): Writer =
            Writer(
                id = WriterId.generate(),
                name = name
            )
    }
}
