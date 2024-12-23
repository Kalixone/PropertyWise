package com.propertywise.service.impl

import com.propertywise.dto.CreatePropertyRequestDto
import com.propertywise.dto.PropertyDto
import com.propertywise.repository.PropertyRepository
import com.propertywise.service.PropertyService
import com.propertywise.toModel
import com.propertywise.toPropertyDto
import org.springframework.stereotype.Service

@Service
class PropertyServiceImpl(
    private val propertyRepository: PropertyRepository)
    : PropertyService {

    override fun addProperty(createPropertyRequestDto: CreatePropertyRequestDto): PropertyDto {
        val propertyModel = createPropertyRequestDto.toModel()
        propertyRepository.save(propertyModel)
        return propertyModel.toPropertyDto()
    }
}
