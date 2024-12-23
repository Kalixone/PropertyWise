package com.propertywise.model

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import java.math.BigDecimal

@Entity
data class FinancialDetails(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long?,
    val monthlyRent: BigDecimal,
    val additionalCosts: BigDecimal,
    val deposit: BigDecimal
)
