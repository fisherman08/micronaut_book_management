package com.github.fisherman08.micronautbooks.controller.writer

import com.github.fisherman08.micronautbooks.controller.ApiPaths
import com.github.fisherman08.micronautbooks.domain.writer.Writer
import com.github.fisherman08.micronautbooks.usecase.writer.WriterListUseCase
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import java.util.*

@Controller(ApiPaths.Writer.list)
class WriterListController(
    private val getWriterList: WriterListUseCase
) {
    @Get
    fun getlist(): List<ResponseBody> = ResponseBody.toResponseBody(getWriterList())

    data class ResponseBody(val id: UUID, val name: String) {
        companion object {
            fun toResponseBody(writers: List<Writer>): List<ResponseBody> =
                writers.map { ResponseBody(id = it.id.value, name = it.name.value) }
        }
    }
}
