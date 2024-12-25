package com.propertywise.dto

data class LocationPatchRequestDto(val address: String? = null, val city: String? = null, val postalCode: String? = null, val neighborhood: String? = null)
