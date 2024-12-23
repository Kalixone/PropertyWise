package com.propertywise.repository

import com.propertywise.model.FinancialDetails
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface FinancialDetailsRepository: JpaRepository<FinancialDetails, Long>
