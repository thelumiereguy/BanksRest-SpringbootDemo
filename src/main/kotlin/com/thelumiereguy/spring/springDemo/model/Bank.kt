package com.thelumiereguy.spring.springDemo.model

import javax.persistence.*

data class Bank(
    val accountNumber: String,
    val trust: Double,
    val transactionFee: Int
) {

    fun toBankEntity(): BankEntity {
        return BankEntity(
            accountNumber = accountNumber,
            trust = trust,
            transactionFee = transactionFee
        )
    }
}

@Entity
data class BankEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0,

    @Column(nullable = false)
    val accountNumber: String = "",

    @Column(nullable = false)
    val trust: Double = 0.0,

    @Column(nullable = false)
    val transactionFee: Int = 0
) {
    fun fromEntity(): Bank {
        return Bank(
            accountNumber,
            trust,
            transactionFee
        )
    }
}