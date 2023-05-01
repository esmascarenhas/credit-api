package com.bootcampTqiDio.creditapi.common.util

import com.bootcampTqiDio.creditapi.domain.entity.Credit
import com.bootcampTqiDio.creditapi.domain.enummeration.Status
import io.mockk.every
import io.mockk.mockk
import java.math.BigDecimal
import java.time.LocalDate
import java.util.*

object CreditBuilder {

    val validCredit: Credit = mockk(relaxed = true) {
        every { creditCode } returns UUID.randomUUID()
        every { creditValue } returns BigDecimal.valueOf(10)
        every { dayFirstInstallment } returns LocalDate.now()
        every { numberOfInstallments } returns 2
        every { status } returns Status.APPROVED
        every { customer } returns CustomerBuilder.customer
    }

    val invalidDateCredit: Credit
        get() = mockk(relaxed = true) {
            every { creditCode } returns UUID.randomUUID()
            every { creditValue } returns BigDecimal.valueOf(10)
            every { dayFirstInstallment } returns LocalDate.now().plusMonths(4)
            every { numberOfInstallments } returns 2
            every { status } returns Status.APPROVED
            every { customer } returns CustomerBuilder.customer
        }
}