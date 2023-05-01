package com.bootcampTqiDio.creditapi.domain.exception

data class BusinessException(override val message: String?) : RuntimeException(message)