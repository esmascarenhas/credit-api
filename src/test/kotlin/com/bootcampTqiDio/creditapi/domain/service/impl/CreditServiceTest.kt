package com.bootcampTqiDio.creditapi.domain.service.impl

import com.bootcampTqiDio.creditapi.common.util.CreditBuilder.invalidDateCredit
import com.bootcampTqiDio.creditapi.common.util.CreditBuilder.validCredit
import com.bootcampTqiDio.creditapi.common.util.CustomerBuilder.customer
import com.bootcampTqiDio.creditapi.domain.entity.Credit
import com.bootcampTqiDio.creditapi.domain.exception.BusinessException
import com.bootcampTqiDio.creditapi.domain.repository.CreditRepository
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.junit5.MockKExtension
import io.mockk.verify
import org.assertj.core.api.Assertions
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import java.util.*

@ExtendWith(MockKExtension::class)
class CreditServiceTest {


    @InjectMockKs
    lateinit var creditService: CreditService

    @RelaxedMockK
    lateinit var creditRepository: CreditRepository

    @RelaxedMockK
    lateinit var customerService: CustomerService

    @BeforeEach
    fun setUp() {
        creditService = CreditService(creditRepository, customerService)
    }

    @Test
    fun `when saving a credit, should bind it to a specific customer`() {
        val id = Random().nextLong()
        every { customerService.findById(id) } returns customer
        every { creditRepository.save(any()) } returns validCredit

        val actual: Credit = creditService.save(validCredit)

        assertThat(actual).isSameAs(validCredit)
        verify(exactly = 1) { creditRepository.save(any()) }
    }

    @Test
    fun `when saving a credit with an invalid date, should throw a business exception`() {
        val id = Random().nextLong()
        every { customerService.findById(id) } returns customer
        every { creditRepository.save(any()) } returns invalidDateCredit

        Assertions.assertThatExceptionOfType(BusinessException::class.java)
                .isThrownBy { creditService.save(invalidDateCredit) }
                .withMessage("Invalid Date")
    }

    @Test
    fun `when searching for credits by customer, should return a list of credits`() {
        val id = Random().nextLong()
        every { creditRepository.findAllByCustomerId(id) } returns listOf(validCredit)

        val actual = creditService.findAllByCustomer(id)

        assertThat(actual[0]).isSameAs(validCredit)
    }

    @Test
    fun `when searching for credit, should return credit if customer is credit owner`() {
        val customerId = Random().nextLong()
        val customerCreditCode = UUID.randomUUID()
        val credit = validCredit.apply {
            every { creditCode } returns customerCreditCode
            every { customer!!.id } returns customerId
        }
        every { creditRepository.findByCreditCode(customerCreditCode) } returns credit

        val actual = creditService.findByCreditCode(customerId, customerCreditCode)

        assertThat(actual).isExactlyInstanceOf(Credit::class.java)
        assertThat(actual).isSameAs(validCredit)
    }

    @Test
    fun `when searching for credit, should throw business exception if creditCode doesn't exist`() {
        val id = Random().nextLong()
        val creditCode = UUID.randomUUID()
        every { creditRepository.findByCreditCode(creditCode) } returns null

        Assertions.assertThatExceptionOfType(BusinessException::class.java)
                .isThrownBy { creditService.findByCreditCode(id, creditCode) }
                .withMessage("Creditcode $creditCode not found")
    }

    @Test
    fun `when searching for credit, throw IllegalArgumentException if customer is not the credit owner`() {
        val id = Random().nextLong()
        val creditCode = UUID.randomUUID()
        every { creditRepository.findByCreditCode(creditCode) } returns validCredit

        Assertions.assertThatExceptionOfType(IllegalArgumentException::class.java)
                .isThrownBy { creditService.findByCreditCode(id, creditCode) }
                .withMessage("Contact admin")
    }
}