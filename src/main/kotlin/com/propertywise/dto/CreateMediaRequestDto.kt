package com.propertywise.dto

data class CreateMediaRequestDto(val photos: MutableList<String> = mutableListOf(), val videoLink: String)
