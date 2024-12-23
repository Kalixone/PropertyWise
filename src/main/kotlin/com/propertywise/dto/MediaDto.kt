package com.propertywise.dto

data class MediaDto(val id: Long, val photos: MutableList<String> = mutableListOf(), val videoLink: String)
