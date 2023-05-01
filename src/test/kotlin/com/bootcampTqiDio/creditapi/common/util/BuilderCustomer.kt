package com.bootcampTqiDio.creditapi.common.util

import com.bootcampTqiDio.creditapi.api.dto.request.CustomerDto
import com.bootcampTqiDio.creditapi.domain.entity.Address
import com.bootcampTqiDio.creditapi.domain.entity.Customer
import org.springframework.stereotype.Component
import org.springframework.stereotype.Service
import java.math.BigDecimal

@Service
class BuilderCustomer {

    fun builderCustomerDto(
            firstName: String = "Emerson",
            lastName: String = "Mascarenhas",
            cpf: String = "28475934625",
            email: String = "esm@email.com",
            income: BigDecimal = BigDecimal.valueOf(1000.0),
            password: String = "1234",
            zipCode: String = "000000",
            street: String = "Rua Silveira Pontes, 123",
    ) = CustomerDto(
            firstName = firstName,
            lastName = lastName,
            cpf = cpf,
            email = email,
            income = income,
            password = password,
            zipCode = zipCode,
            street = street
    )
     fun builderCustomer(
            firstName: String = "Josi",
            lastName: String = "Mascarenhas",
            cpf: String = "28475934625",
            email: String = "josi@gmail.com",
            password: String = "12345",
            zipCode: String = "12345",
            street: String = "Rua da varzea",
            income: BigDecimal = BigDecimal.valueOf(8000.0),
    ) = Customer(
            firstName = firstName,
            lastName = lastName,
            cpf = cpf,
            email = email,
            password = password,
            address = Address(
                    zipCode = zipCode,
                    street = street,
            ),
            income = income,
    )
}