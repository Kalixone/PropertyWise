package com.propertywise.service

import com.propertywise.dto.CreatePropertyRequestDto
import com.propertywise.dto.PropertyDto
import com.propertywise.dto.PropertyPatchRequestDto
import org.springframework.stereotype.Service

@Service
interface PropertyService {
    fun createProperty(createPropertyRequestDto: CreatePropertyRequestDto) : PropertyDto

    fun getById(id: Long) : PropertyDto

    fun deleteById(id: Long)

    fun getAll() : List<PropertyDto>

    fun partialUpdate(id: Long, propertyPatchRequestDto: PropertyPatchRequestDto) : PropertyDto
}
