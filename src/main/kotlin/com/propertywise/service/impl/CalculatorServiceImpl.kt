package com.propertywise.service.impl

import com.propertywise.dto.*
import com.propertywise.service.CalculatorService
import org.springframework.stereotype.Service
import kotlin.math.pow
import kotlin.math.roundToInt

@Service
class CalculatorServiceImpl: CalculatorService {

    override fun loanCalculation(loanCalculationRequest: LoanCalculationRequest): LoanCalculationResult {
        val loanAmount = loanCalculationRequest.loanAmount
        val annualInterestRate = loanCalculationRequest.annualInterestRate
        val loanTermInYears = loanCalculationRequest.loanTermInYears

        val monthlyRate = (annualInterestRate / 100) / 12

        val months = loanTermInYears * 12

        val emi = if (monthlyRate > 0) {
            (loanAmount * monthlyRate * (1 + monthlyRate).pow(months.toDouble())) /
                    ((1 + monthlyRate).pow(months.toDouble()) - 1)
        } else {
            loanAmount / months
        }

        val totalPayment = emi * months

        val totalInterest = totalPayment - loanAmount

        val monthlyPaymentFormatted = (emi * 100.0).roundToInt() / 100.0
        val totalPaymentFormatted = (totalPayment * 100.0).roundToInt() / 100.0
        val totalInterestFormatted = (totalInterest * 100.0).roundToInt() / 100.0

        return LoanCalculationResult(
            monthlyPayment = monthlyPaymentFormatted,
            totalPayment = totalPaymentFormatted,
            totalInterest = totalInterestFormatted
        )
    }

    override fun loanEligibilityCalculator(loanEligibilityCalculatorRequest: LoanEligibilityCalculatorRequest): LoanEligibilityCalculatorResult {
        val netIncome = loanEligibilityCalculatorRequest.netIncome
        val maintenanceCosts = loanEligibilityCalculatorRequest.maintenanceCosts
        val otherObligations = loanEligibilityCalculatorRequest.otherObligations

        val availableNetIncome = netIncome - maintenanceCosts - otherObligations
        val maximumLoanInstallment = availableNetIncome * 40 / 100

        return LoanEligibilityCalculatorResult(maximumLoanInstallment)
    }

    override fun depositContributionCalculator(request: DepositContributionRequest): DepositContributionResult {
        val propertyValue = request.propertyValue
        val depositPercentage = request.depositPercentage

        val depositAmount = propertyValue * (depositPercentage / 100)

        return DepositContributionResult(depositAmount)
    }

    override fun calculateRentalYield(rentalYieldCalculationRequest: RentalYieldCalculationRequest): RentalYieldCalculationResult {
        val monthlyRentalIncome = rentalYieldCalculationRequest.monthlyRentalIncome
        val purchasePrice = rentalYieldCalculationRequest.purchasePrice

        val returnOfInvestment = Math.round((monthlyRentalIncome * 12) / purchasePrice * 100 * 100.0) / 100.0

        return RentalYieldCalculationResult("$returnOfInvestment%")
    }

    override fun calculateProportionalRentalCost(proportionalRentalCostRequest: ProportionalRentalCostRequest): ProportionalRentalCostResult {
        val roomArea = proportionalRentalCostRequest.rentedRoomArea
        val totalArea = proportionalRentalCostRequest.totalArea
        val totalMonthlyRent = proportionalRentalCostRequest.totalMonthlyRent

        val proportionalRentalCost = roomArea / totalArea * totalMonthlyRent

        val roundedProportionalRentalCost = Math.round(proportionalRentalCost * 100.0) / 100.0

        return ProportionalRentalCostResult(roundedProportionalRentalCost)
    }
}
