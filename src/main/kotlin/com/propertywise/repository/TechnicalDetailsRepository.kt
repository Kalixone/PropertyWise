package com.propertywise.repository

import com.propertywise.model.TechnicalDetails
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface TechnicalDetailsRepository: JpaRepository<TechnicalDetails, Long>
