package com.thelumiereguy.spring.springDemo.datasource.fake

import com.thelumiereguy.spring.springDemo.datasource.IBankDataSource
import com.thelumiereguy.spring.springDemo.model.Bank
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.cglib.core.EmitUtils
import org.springframework.stereotype.Repository
import javax.annotation.sql.DataSourceDefinition

@Qualifier("FakeBankDataSource")
class FakeBankDataSource : IBankDataSource {

    private val banks = mutableListOf(
        Bank("12345", 3.14, 1),
        Bank("12351", 13.14, 0),
        Bank("13412", 0.0, 1),
    )

    override fun retrieveBanks(): List<Bank> {
        return banks
    }

    override fun getBank(accountNumber: String): Bank {
        return retrieveBanks().firstOrNull { it.accountNumber == accountNumber }
            ?: throw NoSuchElementException("Could not find a bank with account number $accountNumber")
    }

    override fun addBank(bank: Bank): Bank {
        if (banks.any { it.accountNumber == bank.accountNumber }) {
            throw IllegalArgumentException("Bank with account number already exists")
        }

        banks.add(bank)

        return bank
    }

    override fun updateBank(updatedBank: Bank): Bank {
        val bank = getBank(updatedBank.accountNumber)
        banks.remove(bank)
        banks.add(updatedBank)
        return updatedBank
    }

    override fun deleteBank(accountNumber: String) {
        val bank = getBank(accountNumber)
        banks.remove(bank)
    }

}