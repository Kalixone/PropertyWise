package com.propertywise.dto

import com.propertywise.model.Status
import java.time.LocalDate

data class OfferStatusDto(val id: Long, val status: Status, val createdDate: LocalDate, val lastUpdatedDate: LocalDate)
