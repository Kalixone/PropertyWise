package com.propertywise.service

import com.propertywise.dto.*
import org.springframework.stereotype.Service

@Service
interface CalculatorService {
    fun loanCalculation(loanCalculationRequest: LoanCalculationRequest): LoanCalculationResult
    fun loanEligibilityCalculator(loanEligibilityCalculatorRequest: LoanEligibilityCalculatorRequest): LoanEligibilityCalculatorResult
    fun depositContributionCalculator(request: DepositContributionRequest): DepositContributionResult
    fun calculateRentalYield(rentalYieldCalculationRequest: RentalYieldCalculationRequest): RentalYieldCalculationResult
    fun calculateProportionalRentalCost(proportionalRentalCostRequest: ProportionalRentalCostRequest): ProportionalRentalCostResult
}
