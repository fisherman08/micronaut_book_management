package com.github.fisherman08.micronautbooks.domain.book

import com.github.fisherman08.micronautbooks.domain.writer.Writer

data class Authors(val list: List<Writer>) : Collection<Writer> by list
