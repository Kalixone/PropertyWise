package com.propertywise.dto

data class LoanCalculationRequest(val loanAmount: Double, val annualInterestRate: Double, val loanTermInYears: Int)
