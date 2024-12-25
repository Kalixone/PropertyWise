package com.propertywise.dto

import com.propertywise.model.Type
import java.math.BigDecimal

data class PropertyDto(
    val id: Long,
    val title: String,
    val description: String,
    val price: BigDecimal,
    val pricePerSquareMeter: Double,
    val area: Double,
    val type: Type,
    val location: LocationDto,
    val technicalDetails: TechnicalDetailsDto,
    val additionalFeatures: AdditionalFeaturesDto,
    val media: MediaDto,
    val status: OfferStatusDto,
    val financialDetails: FinancialDetailsDto,
)
