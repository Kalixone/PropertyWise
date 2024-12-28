package com.propertywise.dto

data class LoanEligibilityCalculatorRequest(val netIncome: Double, val maintenanceCosts: Double, val otherObligations: Double)
