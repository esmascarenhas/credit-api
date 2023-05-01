package com.bootcampTqiDio.creditapi.common.util

import com.bootcampTqiDio.creditapi.api.dto.request.CustomerDto
import com.bootcampTqiDio.creditapi.domain.entity.Customer
import io.mockk.every
import io.mockk.mockk
import java.math.BigDecimal

object CustomerBuilder {

    val customer: Customer
        get() = mockk(relaxed = true) {
            every { firstName } returns "Josi"
            every { lastName } returns "Mascarenhas"
            every { cpf } returns "123456789-00"
            every { email } returns "josi@gmail.com"
            every { password } returns "12345"
            every { address } returns mockk(relaxed = true) {
                every { zipCode } returns "12345"
                every { street } returns "street"
            }
            every { income } returns BigDecimal.valueOf(8000.0)
            every { id } returns 1L
        }
    val customerDto: CustomerDto
        get() = mockk(relaxed = true) {
            every { firstName } returns "Emerson"
            every { lastName } returns "Mascarenhas"
            every { cpf } returns "28475934625"
            every { email } returns "esm@email.com"
            every { password } returns "esm1234"
            every { income } returns BigDecimal.valueOf(1000.0)
        }
}