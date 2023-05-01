package com.bootcampTqiDio.creditapi.domain.service.impl

import com.bootcampTqiDio.creditapi.domain.entity.Customer
import com.bootcampTqiDio.creditapi.domain.exception.BusinessException
import com.bootcampTqiDio.creditapi.domain.repository.CustomerRepository
import com.bootcampTqiDio.creditapi.domain.service.ICustomerService
import org.springframework.stereotype.Service

@Service
class CustomerService(
        private val customerRepository: CustomerRepository
): ICustomerService {

    override fun save(customer: Customer): Customer = this.customerRepository.save(customer)

    override fun findById(id: Long): Customer = this.customerRepository.findById(id)
            .orElseThrow{throw BusinessException("Id $id not found") }

    override fun delete(id: Long) {
        val customer: Customer = this.findById(id)
        this.customerRepository.delete(customer)
    }


}