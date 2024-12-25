package com.propertywise.dto

data class MediaPatchRequestDto(val photos: MutableList<String>? = null, val videoLink: String? = null)
