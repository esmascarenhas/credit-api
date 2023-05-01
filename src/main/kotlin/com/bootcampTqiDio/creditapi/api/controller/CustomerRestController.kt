package com.bootcampTqiDio.creditapi.api.controller

import com.bootcampTqiDio.creditapi.api.controller.interfaces.CustomerRestControllerInteface
import com.bootcampTqiDio.creditapi.api.dto.request.CustomerDto
import com.bootcampTqiDio.creditapi.api.dto.request.CustomerUpdateDto
import com.bootcampTqiDio.creditapi.api.dto.response.CustomerView
import com.bootcampTqiDio.creditapi.domain.entity.Customer
import com.bootcampTqiDio.creditapi.domain.service.impl.CustomerService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.ArraySchema
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/customers")
class CustomerRestController(
        private val customerService: CustomerService
): CustomerRestControllerInteface {


    @PostMapping
    override fun saveCustomer(@RequestBody @Valid customerDto: CustomerDto): ResponseEntity<CustomerView> {
        val savedCustomer: Customer = this.customerService.save(customerDto.toEntity())
        return ResponseEntity.status(HttpStatus.CREATED).body(CustomerView(savedCustomer))
    }

    @GetMapping("/{id}")
    override fun findById(@PathVariable id: Long): ResponseEntity<CustomerView> {
        val customer: Customer = this.customerService.findById(id)
        return ResponseEntity.status(HttpStatus.OK).body(CustomerView(customer))
    }

    @Operation(summary = "Deleted Customer")
    @ApiResponses(value = [
        ApiResponse(responseCode = "204", description = "Deleted Customer", content = [
            (Content(mediaType = "application/json", array = (
                    ArraySchema(schema = Schema(implementation = Customer::class)))))])])
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    override fun deleteCustomer(@PathVariable id: Long) = this.customerService.delete(id)


    @PatchMapping
    override fun upadateCustomer(
            @RequestParam(value = "customerId") id: Long,
            @RequestBody @Valid customerUpdateDto: CustomerUpdateDto
    ): ResponseEntity<CustomerView> {
        val customer: Customer = this.customerService.findById(id)
        val cutomerToUpdate: Customer = customerUpdateDto.toEntity(customer)
        val customerUpdated: Customer = this.customerService.save(cutomerToUpdate)
        return ResponseEntity.status(HttpStatus.OK).body(CustomerView(customerUpdated))
    }
}