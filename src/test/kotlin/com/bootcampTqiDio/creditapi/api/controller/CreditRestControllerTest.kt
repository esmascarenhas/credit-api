package com.bootcampTqiDio.creditapi.api.controller

import com.bootcampTqiDio.creditapi.api.dto.request.CreditDto
import com.bootcampTqiDio.creditapi.common.util.CustomerBuilder.customer
import com.bootcampTqiDio.creditapi.domain.entity.Credit
import com.bootcampTqiDio.creditapi.domain.entity.Customer
import com.bootcampTqiDio.creditapi.domain.repository.CreditRepository
import com.bootcampTqiDio.creditapi.domain.repository.CustomerRepository
import com.fasterxml.jackson.databind.ObjectMapper
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultHandlers
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import java.math.BigDecimal
import java.time.LocalDate

@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc
@ContextConfiguration
class CreditRestControllerTest {
    @Autowired
    private lateinit var creditRepository: CreditRepository
    @Autowired
    private lateinit var customerRepository: CustomerRepository
    @Autowired
    private lateinit var mockMvc: MockMvc
    @Autowired
    private lateinit var objectMapper: ObjectMapper

    companion object{
        const val URL: String = "/api/credits"
    }

    @BeforeEach
    fun setup() {
        creditRepository.deleteAll()
        customerRepository.deleteAll()
    }
    @AfterEach
    fun tearDown() {
        creditRepository.deleteAll()
        customerRepository.deleteAll()
    }

    @Test
    fun `should create a credit and return 201 status`() {
        //given
        val customer: Customer = customerRepository.save(customer)
        val credit: CreditDto = builderCreditDto(customerId = customer.id!!.toLong())
        //Assertions.assertThat(creditDto.customerId).isEqualTo(2)

        val valueAsString: String = objectMapper.writeValueAsString(credit)
        //when

        //then
        mockMvc.perform(
                MockMvcRequestBuilders.post(URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(valueAsString)
        ).andExpect(MockMvcResultMatchers.status().isCreated)
                .andDo(MockMvcResultHandlers.print())


    }

    @Test
    fun `should find all credit by customerId and return 200 status`() {
        //given
        val customer: Customer = customerRepository.save(customer)
        val credit: Credit = creditRepository.save(builderCreditDto(customerId = customer.id!!.toLong()).toEntity())
        val valueAsString: String = objectMapper.writeValueAsString(credit)

        //when

        //then
        mockMvc.perform(
                MockMvcRequestBuilders.get("$URL?customerId=${customer.id}")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(valueAsString)
        ).andExpect(MockMvcResultMatchers.status().isOk)
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].creditValue").value(2000.0))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].numberOfInstallments").value("5"))
                .andDo(MockMvcResultHandlers.print())


    }

    @Test
    fun `should find credit by customerId, creditCode and return 200 status`() {
        //given
        customerRepository.save(customer)
        val credit: Credit = creditRepository.save(builderCreditDto().toEntity())
        val valueAsString: String = objectMapper.writeValueAsString(credit)

        //when

        //then
        mockMvc.perform(
                MockMvcRequestBuilders.get("$URL/${credit.creditCode}?customerId=1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(valueAsString)
        ).andExpect(MockMvcResultMatchers.status().isOk)
                .andExpect(MockMvcResultMatchers.jsonPath("$.creditValue").value(2000.0))
                .andExpect(MockMvcResultMatchers.jsonPath("$.emailCustomer").value("josi@gmail.com"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.status").value("IN_PROGRESS"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.incomeCustomer").value(8000.0))
                .andDo(MockMvcResultHandlers.print())

    }

    private fun builderCreditDto(
            creditValue: BigDecimal = BigDecimal.valueOf(2000.0),
            dayFirstOfInstallment: LocalDate = LocalDate.now().plusDays(45),
            numberOfInstallments: Int = 5,
            customerId: Long = 1L
    ) = CreditDto(
            creditValue = creditValue,
            dayFirstOfInstallment = dayFirstOfInstallment,
            numberOfInstallments = numberOfInstallments,
            customerId = customerId
    )
}