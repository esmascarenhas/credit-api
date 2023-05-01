package com.bootcampTqiDio.creditapi.api.controller.interfaces

import com.bootcampTqiDio.creditapi.api.dto.request.CreditDto
import com.bootcampTqiDio.creditapi.api.dto.response.CreditView
import com.bootcampTqiDio.creditapi.api.dto.response.CreditViewList
import com.bootcampTqiDio.creditapi.domain.entity.Credit
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.ArraySchema
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema

import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestParam
import java.util.*


interface CreditRestControllerInterface {


    @Operation(summary = "Save a Credit")
    @ApiResponses(value = [
        ApiResponse(responseCode = "201", description = "Created Credit", content = [
            (Content(mediaType = "application/json", array = (
                    ArraySchema(schema = Schema(implementation = Credit::class)))))]),
        ApiResponse(responseCode = "400", description = "Bad request", content = [Content()])])
    fun saveCredit(@RequestBody @Valid creditDto: CreditDto): ResponseEntity<String>

    @Operation(summary = "Found a Credit by Customer Code")
    @ApiResponses(value = [
        ApiResponse(responseCode = "200", description = "Found Credit", content = [
            (Content(mediaType = "application/json", array = (
                    ArraySchema(schema = Schema(implementation = Credit::class)))))]),
        ApiResponse(responseCode = "400", description = "Bad request", content = [Content()])])
    fun findAllByCustomerId(@RequestParam(value = "customerId") customerId: Long):
            ResponseEntity<List<CreditViewList>>

    @Operation(summary = "Found a Credit by Credit Code")
    @ApiResponses(value = [
        ApiResponse(responseCode = "200", description = "Found Credit", content = [
            (Content(mediaType = "application/json", array = (
                    ArraySchema(schema = Schema(implementation = Credit::class)))))]),
        ApiResponse(responseCode = "400", description = "Bad request", content = [Content()])])
    fun findByCreditCode(
            @RequestParam(value = "customerId") customerId: Long,
            @PathVariable creditCode: UUID
    ): ResponseEntity<CreditView>
}