package com.bootcampTqiDio.creditapi.common.util

import com.bootcampTqiDio.creditapi.api.dto.request.CreditDto
import com.bootcampTqiDio.creditapi.domain.entity.Credit
import com.bootcampTqiDio.creditapi.domain.entity.Customer
import org.springframework.stereotype.Component
import org.springframework.stereotype.Service
import java.math.BigDecimal
import java.time.LocalDate

@Component
class BuilderCredit {

    fun builderCredit(
            creditValue: BigDecimal = BigDecimal.valueOf(80750),
            dayFirstInstallment: LocalDate = LocalDate.of(28,8,23),
            numberOfInstallments: Int = 25,
            customer: Customer
    ): Credit = Credit(
            creditValue = creditValue,
            dayFirstInstallment = dayFirstInstallment,
            numberOfInstallments = numberOfInstallments,
            customer = customer
    )
    fun builderCreditDto(
            creditValue: BigDecimal = BigDecimal.valueOf(80750.0),
            dayFirstOfInstallment: LocalDate = LocalDate.of(2023,8,23),
            numberOfInstallments: Int = 25,
            customerId: Long = 1L
    ) = CreditDto(
            creditValue = creditValue,
            dayFirstOfInstallment = dayFirstOfInstallment,
            numberOfInstallments = numberOfInstallments,
            customerId = customerId
    )
}