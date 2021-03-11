package com.thelumiereguy.spring.springDemo.controller

import com.thelumiereguy.spring.springDemo.model.Bank
import com.thelumiereguy.spring.springDemo.service.BankService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("api")
class BankController(private val service: BankService) {

    @ExceptionHandler(NoSuchElementException::class)
    fun handleNotFound(exception: NoSuchElementException): ResponseEntity<String> =
        ResponseEntity(exception.message, HttpStatus.NOT_FOUND)


    @ExceptionHandler(IllegalArgumentException::class)
    fun handleBadRequest(exception: IllegalArgumentException): ResponseEntity<String> =
        ResponseEntity(exception.message, HttpStatus.BAD_REQUEST)


    @GetMapping("/banks")
    fun getBanks(): List<Bank> {
        return service.getBanks()
    }

    @GetMapping("/bank/{accountNumber}")
    fun getBank(@PathVariable accountNumber: String) = service.getBank(accountNumber)

    @PostMapping("/bank")
    @ResponseStatus(HttpStatus.CREATED)
    fun insertBank(@RequestBody bank: Bank): Bank = service.addBank(bank)

    @PatchMapping("/bank")
    fun updateBank(@RequestBody bank: Bank): Bank = service.updateBank(bank)


    @DeleteMapping("/bank/{accountNumber}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteBank(@PathVariable accountNumber: String)  = service.deleteBank(accountNumber)
}