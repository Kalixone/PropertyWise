package com.propertywise.dto

import com.propertywise.model.Type
import java.math.BigDecimal

data class PropertyPatchRequestDto(
    val title: String? = null,
    val description: String? = null,
    val price: BigDecimal? = null,
    val area: Double? = null,
    val type: Type? = null,
    val location: LocationPatchRequestDto? = null,
    val technicalDetails: TechnicalDetailsPatchRequestDto? = null,
    val additionalFeatures: AdditionalFeaturesPatchRequestDto? = null,
    val media: MediaPatchRequestDto? = null,
    val status: OfferStatusPatchRequestDto? = null,
    val financialDetails: FinancialDetailsPatchRequestDto? = null
)
