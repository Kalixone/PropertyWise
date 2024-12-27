package com.propertywise.dto

import java.math.BigDecimal

data class UserDto(val id: Long?, val email: String, val firstName: String, val lastName: String, val areaThresholdForNotifications: Double?, val priceThresholdForNotifications: BigDecimal?)
