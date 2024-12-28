package com.propertywise.controller

import com.propertywise.dto.*
import com.propertywise.service.CalculatorService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/calculator")
@Tag(name = "Loan Calculator", description = "Endpoints for loan calculations, including loan eligibility and loan amount calculations.")
class CalculatorController(private val calculatorService: CalculatorService) {

    @PostMapping("/loan-calculation")
    @PreAuthorize("hasAuthority('USER')")
    @Operation(summary = "Loan calculation", description = "Calculates the loan amount and monthly installment based on user input, including income, expenses, and loan terms.")
    fun loanCalculator(@RequestBody loanCalculationRequest: LoanCalculationRequest): LoanCalculationResult {
        return calculatorService.loanCalculation(loanCalculationRequest)
    }

    @PostMapping("/loan-eligibility")
    @PreAuthorize("hasAuthority('USER')")
    @Operation(summary = "Loan eligibility calculation", description = "Calculates the maximum loan installment a user can afford based on their net income, maintenance costs, and other financial obligations.")
    fun loanEligibilityCalculator(@RequestBody loanEligibilityCalculatorRequest: LoanEligibilityCalculatorRequest): LoanEligibilityCalculatorResult {
        return calculatorService.loanEligibilityCalculator(loanEligibilityCalculatorRequest)
    }

    @PostMapping("/deposit-contribution")
    @PreAuthorize("hasAuthority('USER')")
    @Operation(summary = "Deposit contribution calculation", description = "Calculates the required deposit contribution (e.g., 20% of the property value) for a mortgage.")
    fun depositContributionCalculator(@RequestBody depositContributionRequest: DepositContributionRequest): DepositContributionResult {
        return calculatorService.depositContributionCalculator(depositContributionRequest)
    }

    @PostMapping("/calculate-rental-yield")
    @PreAuthorize("hasAuthority('USER')")
    @Operation(summary = "Calculate rental yield", description = "Calculates the rental yield based on the provided monthly rental income and property purchase price.")
    fun calculateRentalYield(@RequestBody rentalYieldCalculationRequest: RentalYieldCalculationRequest): RentalYieldCalculationResult {
        return calculatorService.calculateRentalYield(rentalYieldCalculationRequest)
    }

    @PostMapping("/proportional-rental-cost")
    @PreAuthorize("hasAuthority('USER')")
    @Operation(summary = "Calculate proportional rental cost", description = "Calculates the rental cost per person based on shared space and total rent.")
    fun calculateProportionalRentalCost(@RequestBody proportionalRentalCostRequest: ProportionalRentalCostRequest): ProportionalRentalCostResult {
        return calculatorService.calculateProportionalRentalCost(proportionalRentalCostRequest)
    }
}
