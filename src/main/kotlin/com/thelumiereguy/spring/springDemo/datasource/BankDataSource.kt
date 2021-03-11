package com.thelumiereguy.spring.springDemo.datasource

import com.thelumiereguy.spring.springDemo.model.Bank
import com.thelumiereguy.spring.springDemo.repository.BankRepository
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Component
import javax.transaction.Transactional

@Component
@Qualifier("BankDataSource")
class BankDataSource(private val bankRepository: BankRepository) : IBankDataSource {
    override fun retrieveBanks(): List<Bank> {
        val allBanks = bankRepository.findAll()
        return allBanks.map {
            it.fromEntity()
        }
    }

    override fun getBank(accountNumber: String): Bank {
        return bankRepository.findBankEntityByAccountNumber(accountNumber).fromEntity()
    }

    @Transactional
    override fun addBank(bank: Bank): Bank {
        val entity = bank.toBankEntity()
        return bankRepository.save(entity).fromEntity()
    }

    @Transactional
    override fun updateBank(bank: Bank): Bank {
        val entity = bank.toBankEntity()
        return bankRepository.saveAndFlush(entity).fromEntity()
    }

    @Transactional
    override fun deleteBank(accountNumber: String) {
        bankRepository.deleteBankEntityByAccountNumber(accountNumber)
    }

}