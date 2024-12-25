package com.propertywise.dto

import com.propertywise.model.Status
import java.time.LocalDate

data class OfferStatusPatchRequestDto(val status: Status? = null, val createdDate: LocalDate? = null, val lastUpdatedDate: LocalDate? = null)
