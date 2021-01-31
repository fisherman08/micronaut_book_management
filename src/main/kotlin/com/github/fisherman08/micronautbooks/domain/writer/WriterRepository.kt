package com.github.fisherman08.micronautbooks.domain.writer

interface WriterRepository {
    fun listAll(): List<Writer>
    fun find(writerId: WriterId): Writer
    fun save(writer: Writer)
    fun delete(writerId: WriterId)
}
