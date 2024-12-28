package com.propertywise.dto

import com.propertywise.model.SaleOrRent
import com.propertywise.model.Type
import java.math.BigDecimal

data class CreatePropertyRequestDto(
    val title: String,
    val description: String,
    val price: BigDecimal,
    val contactEmail: String,
    val pricePerSquareMeter: Double,
    val area: Double,
    val type: Type,
    val saleOrRent: SaleOrRent,
    val location: CreateLocationRequestDto,
    val technicalDetails: CreateTechnicalDetailsRequestDto,
    val additionalFeatures: CreateAdditionalFeaturesRequestDto,
    val media: CreateMediaRequestDto,
    val offerStatus: CreateOfferStatusRequestDto,
    val financialDetails: CreateFinancialDetailsRequestDto
)
