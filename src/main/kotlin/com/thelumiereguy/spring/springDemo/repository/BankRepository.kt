package com.thelumiereguy.spring.springDemo.repository

import com.thelumiereguy.spring.springDemo.model.BankEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface BankRepository : JpaRepository<BankEntity, Long> {

    override fun findAll(): MutableList<BankEntity>
}