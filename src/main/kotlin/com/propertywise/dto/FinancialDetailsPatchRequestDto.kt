package com.propertywise.dto

import java.math.BigDecimal

data class FinancialDetailsPatchRequestDto(val monthlyRent: BigDecimal? = null, val additionalCosts: BigDecimal? = null, val deposit: BigDecimal? = null)
