package com.propertywise.service

import com.propertywise.dto.CreatePropertyRequestDto
import com.propertywise.dto.PropertyDto
import org.springframework.stereotype.Service

@Service
interface PropertyService {
    fun addProperty(createPropertyRequestDto: CreatePropertyRequestDto) : PropertyDto
}
