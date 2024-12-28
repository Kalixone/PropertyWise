package com.propertywise.dto

import com.propertywise.model.Province

data class CreateLocationRequestDto(val address: String, val city: String, val province: Province, val postalCode: String, val neighborhood: String)
