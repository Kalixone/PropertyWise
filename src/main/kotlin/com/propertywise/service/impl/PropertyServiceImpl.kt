package com.propertywise.service.impl

import com.propertywise.dto.CreatePropertyRequestDto
import com.propertywise.dto.PropertyDto
import com.propertywise.exceptions.PropertyNotFoundException
import com.propertywise.model.Property
import com.propertywise.repository.PropertyRepository
import com.propertywise.service.PropertyService
import com.propertywise.toModel
import com.propertywise.toPropertyDto
import org.springframework.stereotype.Service
import java.lang.Exception

@Service
class PropertyServiceImpl(
    private val propertyRepository: PropertyRepository)
    : PropertyService {

    override fun createProperty(createPropertyRequestDto: CreatePropertyRequestDto): PropertyDto {
        val propertyModel = createPropertyRequestDto.toModel()
        propertyRepository.save(propertyModel)
        return propertyModel.toPropertyDto()
    }

    override fun getById(id: Long): PropertyDto {
        return propertyRepository.findById(id)
            .map(Property::toPropertyDto)
            .orElseThrow { PropertyNotFoundException("Property with ID $id not found") }
    }

    override fun deleteById(id: Long) {
        return propertyRepository.deleteById(id)
    }

    override fun getAll(): List<PropertyDto> {
        return propertyRepository.findAll().map(Property::toPropertyDto).toList()
    }
}
