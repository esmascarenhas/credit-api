package com.bootcampTqiDio.creditapi.api.dto.request

import com.bootcampTqiDio.creditapi.domain.entity.Customer
import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.NotNull
import java.math.BigDecimal

data class CustomerUpdateDto(
        @field:NotEmpty(message = "Invalid input") val firstName: String,
        @field:NotEmpty(message = "Invalid input") val lastName: String,
        @field:NotEmpty(message = "Invalid input") val email: String,
        @field:NotNull(message = "Invalid input") val income: BigDecimal,
        @field:NotEmpty(message = "Invalid input") val zipCode: String,
        @field:NotEmpty(message = "Invalid input") val street: String
) {
    fun toEntity(customer: Customer): Customer {
        customer.firstName = this.firstName
        customer.lastName = this.lastName
        customer.email = this.email
        customer.income = this.income
        customer.address.street = this.street
        customer.address.zipCode = this.zipCode
        return customer
    }
}

