package com.propertywise.dto

import java.math.BigDecimal

data class FinancialDetailsDto(val id: Long, val monthlyRent: BigDecimal, val additionalCosts: BigDecimal, val deposit: BigDecimal)
