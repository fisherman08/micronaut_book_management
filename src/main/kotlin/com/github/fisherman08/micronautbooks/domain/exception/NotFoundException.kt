package com.github.fisherman08.micronautbooks.domain.exception

import kotlin.reflect.KClass

class NotFoundException(klass: KClass<*>) : RuntimeException("Entity not found: ${klass.simpleName}")
