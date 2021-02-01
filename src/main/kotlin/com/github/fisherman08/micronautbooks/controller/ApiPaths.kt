package com.github.fisherman08.micronautbooks.controller

object ApiPaths {
    const val base = "/api"

    object Book {
        const val base = "${ApiPaths.base}/book"
        const val list = "$base/list"
        const val register = "$base"
        const val info = "$base/{id}"
        const val update = "$base/{id}"
        const val delete = "$base/{id}"
    }
}
