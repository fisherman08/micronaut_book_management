package com.github.fisherman08.micronautbooks.domain.writer

data class WriterName(val value: String) {
    companion object {
        fun fromString(string: String): WriterName = WriterName(string)
    }
}
