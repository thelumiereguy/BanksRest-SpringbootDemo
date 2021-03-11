package com.thelumiereguy.spring.springDemo.datasource

import com.thelumiereguy.spring.springDemo.datasource.fake.FakeBankDataSource
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BankDataSourceTest {

    private val datasource: IBankDataSource = FakeBankDataSource()

    @Test
    fun `should provide a list of banks`() {

        val banks = datasource.retrieveBanks()

        assertThat(banks).hasSizeGreaterThanOrEqualTo(3)
    }


    @Test
    fun `test basic validations of banks`() {

        val banks = datasource.retrieveBanks()

        assertThat(banks).allMatch {
            it.accountNumber.isNotBlank()
        }

        assertThat(banks).anyMatch {
            it.trust != 0.0
        }

        assertThat(banks).anyMatch {
            it.transactionFee != 0
        }


    }

}