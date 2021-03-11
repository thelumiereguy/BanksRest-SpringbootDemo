package com.thelumiereguy.spring.springDemo.datasource

import com.thelumiereguy.spring.springDemo.model.Bank

class BankDataSource : IBankDataSource {
    override fun retrieveBanks(): List<Bank> {
        TODO("Not yet implemented")
    }

    override fun getBank(accountNumber: String): Bank {
        TODO("Not yet implemented")
    }

    override fun addBank(bank: Bank): Bank {
        TODO("Not yet implemented")
    }

    override fun updateBank(bank: Bank): Bank {
        TODO("Not yet implemented")
    }

    override fun deleteBank(accountNumber: String) {
        TODO("Not yet implemented")
    }

}