package com.thelumiereguy.spring.springDemo.service

import com.thelumiereguy.spring.springDemo.datasource.IBankDataSource
import com.thelumiereguy.spring.springDemo.model.Bank
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Service

@Service
class BankService(
    @Autowired
    @Qualifier("BankDataSource")
    private val dataSource: IBankDataSource
) {

    fun getBanks(): List<Bank> = dataSource.retrieveBanks()

    fun getBank(accountNumber: String): Bank {
        return dataSource.getBank(accountNumber)
    }

    fun addBank(bank: Bank): Bank = dataSource.addBank(bank)

    fun updateBank(bank: Bank): Bank = dataSource.updateBank(bank)

    fun deleteBank(accountNumber: String) = dataSource.deleteBank(accountNumber)
}