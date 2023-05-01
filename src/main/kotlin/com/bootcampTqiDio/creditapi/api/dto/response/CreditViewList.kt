package com.bootcampTqiDio.creditapi.api.dto.response

import com.bootcampTqiDio.creditapi.domain.entity.Credit
import java.math.BigDecimal
import java.util.*

class CreditViewList(
        val creditCode: UUID,
        val creditValue: BigDecimal,
        val numberOfInstallments: Int
) {
    constructor(credit: Credit) : this(
            creditCode = credit.creditCode,
            creditValue = credit.creditValue,
            numberOfInstallments = credit.numberOfInstallments
    )
}
