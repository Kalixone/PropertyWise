package com.propertywise.dto

import java.math.BigDecimal

data class CreateFinancialDetailsRequestDto(val monthlyRent: BigDecimal, val additionalCosts: BigDecimal, val deposit: BigDecimal)
