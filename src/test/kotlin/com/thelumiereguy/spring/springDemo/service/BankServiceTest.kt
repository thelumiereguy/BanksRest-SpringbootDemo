package com.thelumiereguy.spring.springDemo.service

import com.thelumiereguy.spring.springDemo.datasource.IBankDataSource
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Test

internal class BankServiceTest {

    private val bankDataSource: IBankDataSource = mockk(relaxed = true)

    private val bankService = BankService(bankDataSource)

    @Test
    fun `should call its datasource to retrieve banks`() {

        bankService.getBanks()

        verify(exactly = 1) { bankDataSource.retrieveBanks() }
    }
}