package com.bootcampTqiDio.creditapi.api.controller

import com.bootcampTqiDio.creditapi.api.controller.interfaces.CreditRestControllerInterface
import com.bootcampTqiDio.creditapi.api.dto.request.CreditDto
import com.bootcampTqiDio.creditapi.api.dto.response.CreditView
import com.bootcampTqiDio.creditapi.api.dto.response.CreditViewList
import com.bootcampTqiDio.creditapi.domain.entity.Credit
import com.bootcampTqiDio.creditapi.domain.service.impl.CreditService
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*
import java.util.stream.Collectors


@RestController
@RequestMapping("/api/credits")
class CreditRestController(
        private val creditService: CreditService
): CreditRestControllerInterface {


    @PostMapping
    override fun saveCredit(@RequestBody @Valid creditDto: CreditDto): ResponseEntity<String> {
        val credit: Credit = this.creditService.save(creditDto.toEntity())
        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Credit ${credit.creditCode} - Customer ${credit.customer?.email} saved!")
    }

    @GetMapping
    override fun findAllByCustomerId(@RequestParam(value = "customerId") customerId: Long):
            ResponseEntity<List<CreditViewList>> {
        val creditViewList: List<CreditViewList> = this.creditService.findAllByCustomer(customerId)
                .stream()
                .map { credit: Credit -> CreditViewList(credit) }
                .collect(Collectors.toList())
        return ResponseEntity.status(HttpStatus.OK).body(creditViewList)
    }

    @GetMapping("/{creditCode}")
    override fun findByCreditCode(
            @RequestParam(value = "customerId") customerId: Long,
            @PathVariable creditCode: UUID
    ): ResponseEntity<CreditView> {
        val credit: Credit = this.creditService.findByCreditCode(customerId, creditCode)
        return ResponseEntity.status(HttpStatus.OK).body(CreditView(credit))
    }
}