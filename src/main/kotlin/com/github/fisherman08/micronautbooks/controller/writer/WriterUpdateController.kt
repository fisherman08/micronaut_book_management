package com.github.fisherman08.micronautbooks.controller.writer

import com.github.fisherman08.micronautbooks.controller.ApiPaths
import com.github.fisherman08.micronautbooks.domain.writer.WriterId
import com.github.fisherman08.micronautbooks.domain.writer.WriterName
import com.github.fisherman08.micronautbooks.usecase.writer.WriterUpdateUseCase
import io.micronaut.http.annotation.*
import java.util.*

@Controller(ApiPaths.Writer.update)
class WriterUpdateController(
    private val update: WriterUpdateUseCase
) {
    @Post
    fun updateWriter(
        @PathVariable id: String,
        @Body body: RequestBody
    ): ResponseBody {
        val writer = update(WriterId.fromString(id), WriterName.fromString(body.name))
        return ResponseBody(id = writer.id.value, name = writer.name.value)
    }

    data class RequestBody(val name: String)
    data class ResponseBody(val id: UUID, val name: String)
}
