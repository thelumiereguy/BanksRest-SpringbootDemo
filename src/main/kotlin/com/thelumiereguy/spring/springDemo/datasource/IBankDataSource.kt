package com.thelumiereguy.spring.springDemo.datasource

import com.thelumiereguy.spring.springDemo.model.Bank

interface IBankDataSource {

    fun retrieveBanks(): List<Bank>
    fun getBank(accountNumber: String): Bank
    fun addBank(bank: Bank): Bank
    fun updateBank(bank: Bank): Bank
    fun deleteBank(accountNumber: String)
}