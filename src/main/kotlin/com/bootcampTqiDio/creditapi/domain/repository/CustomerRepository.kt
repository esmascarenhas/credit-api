package com.bootcampTqiDio.creditapi.domain.repository

import com.bootcampTqiDio.creditapi.domain.entity.Customer
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface CustomerRepository: JpaRepository<Customer, Long> {
}