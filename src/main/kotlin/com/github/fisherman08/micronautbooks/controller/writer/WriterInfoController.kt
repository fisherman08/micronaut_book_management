package com.github.fisherman08.micronautbooks.controller.writer

import com.github.fisherman08.micronautbooks.controller.ApiPaths
import com.github.fisherman08.micronautbooks.domain.writer.WriterId
import com.github.fisherman08.micronautbooks.usecase.writer.WriterInfoUseCase
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.PathVariable
import java.util.*

@Controller(ApiPaths.Writer.update)
class WriterInfoController(
    private val getInfo: WriterInfoUseCase
) {
    @Get
    fun getWriterInfo(
        @PathVariable id: String
    ): ResponseBody {
        val writer = getInfo(WriterId.fromString(id))
        return ResponseBody(id = writer.id.value, name = writer.name.value)
    }

    data class ResponseBody(val id: UUID, val name: String)
}
