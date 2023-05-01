package com.bootcampTqiDio.creditapi.domain.service

import com.bootcampTqiDio.creditapi.domain.entity.Customer

interface ICustomerService {
    fun save(customer: Customer): Customer
    fun findById(id: Long): Customer
    fun delete(id: Long)
}