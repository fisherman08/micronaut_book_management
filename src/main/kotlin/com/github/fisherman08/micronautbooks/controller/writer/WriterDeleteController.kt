package com.github.fisherman08.micronautbooks.controller.writer

import com.github.fisherman08.micronautbooks.controller.ApiPaths
import com.github.fisherman08.micronautbooks.domain.writer.WriterId
import com.github.fisherman08.micronautbooks.usecase.writer.WriterDeleteUseCase
import io.micronaut.http.HttpStatus
import io.micronaut.http.annotation.*

@Controller(ApiPaths.Writer.delete)
class WriterDeleteController(
    private val delete: WriterDeleteUseCase
) {
    @Delete
    @Status(HttpStatus.NO_CONTENT)
    fun deleteWriter(
        @PathVariable id: String
    ) {
        delete(WriterId.fromString(id))
    }

}
