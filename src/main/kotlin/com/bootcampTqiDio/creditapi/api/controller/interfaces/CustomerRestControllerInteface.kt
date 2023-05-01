package com.bootcampTqiDio.creditapi.api.controller.interfaces

import com.bootcampTqiDio.creditapi.api.dto.request.CustomerDto
import com.bootcampTqiDio.creditapi.api.dto.request.CustomerUpdateDto
import com.bootcampTqiDio.creditapi.api.dto.response.CustomerView
import com.bootcampTqiDio.creditapi.domain.entity.Customer
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.media.ArraySchema
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@Schema(description = "Customer Management")
interface CustomerRestControllerInteface {

    @ApiResponses(value = [
        ApiResponse(responseCode = "201", description = "Created Customer", content = [
            (Content(mediaType = "application/json", array = (
                    ArraySchema(schema = Schema(implementation = Customer::class)))))]),
        ApiResponse(responseCode = "400", description = "Bad request", content = [Content()])])
    @Operation(summary = "Save a Customer", description = "Returns 201 if created with successful")
    fun saveCustomer(@RequestBody @Valid customerDto: CustomerDto): ResponseEntity<CustomerView>

    @Operation(summary = "Get a Customer With a ID")
    @ApiResponses(value = [
        ApiResponse(responseCode = "200", description = "Found Customer", content = [
            (Content(mediaType = "application/json", array = (
                    ArraySchema(schema = Schema(implementation = Customer::class)))))]),
        ApiResponse(responseCode = "400", description = "Bad request", content = [Content()])])
    fun findById(@PathVariable id: Long): ResponseEntity<CustomerView>

    @Operation(summary = "Deleted Customer")
    @ApiResponses(value = [
        ApiResponse(responseCode = "204", description = "Deleted Customer", content = [
            (Content(mediaType = "application/json", array = (
                    ArraySchema(schema = Schema(implementation = Customer::class)))))])])
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteCustomer(@PathVariable id: Long)


    @Operation(summary = "Updated Customer")
    @ApiResponses(value = [
        ApiResponse(responseCode = "200", description = "Updated Customer", content = [
            (Content(mediaType = "application/json", array = (
                    ArraySchema(schema = Schema(implementation = Customer::class)))))]),
        ApiResponse(responseCode = "400", description = "Bad request", content = [Content()])])
    fun upadateCustomer(
            @RequestParam(value = "customerId") id: Long,
            @RequestBody @Valid customerUpdateDto: CustomerUpdateDto
    ): ResponseEntity<CustomerView>
}