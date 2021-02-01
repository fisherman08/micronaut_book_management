package com.github.fisherman08.micronautbooks.controller.writer

import com.github.fisherman08.micronautbooks.controller.ApiPaths
import com.github.fisherman08.micronautbooks.domain.writer.WriterName
import com.github.fisherman08.micronautbooks.usecase.writer.WriterRegisterUseCase
import io.micronaut.http.annotation.Body
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Post
import java.util.*

@Controller(ApiPaths.Writer.register)
class WriterRegisterController(
    private val register: WriterRegisterUseCase
) {
    @Post
    fun registerWriter(@Body body: RequestBody): ResponseBody {
        val writer = register(WriterName.fromString(body.name))
        return ResponseBody(id = writer.id.value, name = writer.name.value)
    }

    data class RequestBody(val name: String)
    data class ResponseBody(val id: UUID, val name: String)
}
