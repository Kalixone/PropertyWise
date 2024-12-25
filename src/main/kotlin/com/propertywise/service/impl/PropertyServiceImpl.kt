package com.propertywise.service.impl

import com.propertywise.dto.CreatePropertyRequestDto
import com.propertywise.dto.PropertyDto
import com.propertywise.dto.PropertyPatchRequestDto
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

    override fun partialUpdate(id: Long, propertyPatchRequestDto: PropertyPatchRequestDto): PropertyDto {
        val property = propertyRepository.findById(id).orElseThrow { PropertyNotFoundException(id.toString()) }

        propertyPatchRequestDto.title?.let { property.title = it }
        propertyPatchRequestDto.description?.let { property.description = it }
        propertyPatchRequestDto.price?.let { property.price = it }
        propertyPatchRequestDto.pricePerSquareMeter?.let { property.pricePerSquareMeter = it }
        propertyPatchRequestDto.area?.let { property.area = it }
        propertyPatchRequestDto.type?.let { property.type = it }

        propertyPatchRequestDto.location?.let { locationRequestDto ->
            property.location.apply {
                locationRequestDto.address?.let { this.address = it }
                locationRequestDto.city?.let { this.city = it }
                locationRequestDto.postalCode?.let { this.postalCode = it }
                locationRequestDto.neighborhood?.let { this.neighborhood = it }
            }
        }

        propertyPatchRequestDto.technicalDetails?.let { technicalDto ->
            property.technicalsDetails.apply {
                technicalDto.numberOfRooms?.let { this.numberOfRooms = it }
                technicalDto.floor?.let { this.floor = it }
                technicalDto.totalFloors?.let { this.totalFloors = it }
                technicalDto.yearBuild?.let { this.yearBuild = it }
                technicalDto.heating?.let { this.heating = it }
                technicalDto.constructionMaterial?.let { this.constructionMaterial = it }
                technicalDto.propertyCondition?.let { this.propertyCondition = it }
            }
        }

        propertyPatchRequestDto.additionalFeatures?.let { featuresDto ->
            property.additionalFeatures.apply {
                featuresDto.balcony?.let { this.balcony = it }
                featuresDto.garden?.let { this.garden = it }
                featuresDto.garage?.let { this.garage = it }
                featuresDto.elevator?.let { this.elevator = it }
                featuresDto.furnished?.let { this.furnished = it }
            }
        }

        propertyPatchRequestDto.media?.let { mediaDto ->
            property.media.apply {
                mediaDto.photos?.let { this.photos = it }
                mediaDto.videoLink?.let { this.videoLink = it }
            }
        }

        propertyPatchRequestDto.status?.let { offerStatusDto ->
            property.status.apply {
                offerStatusDto.status?.let { this.status = it }
                offerStatusDto.createdDate?.let { this.createdDate = it }
                offerStatusDto.lastUpdatedDate?.let { this.lastUpdatedDate = it }
            }
        }

        propertyPatchRequestDto.financialDetails?.let { financialDetailsRequestDto ->
            property.financialDetails.apply {
                financialDetailsRequestDto.monthlyRent?.let { this.monthlyRent = it }
                financialDetailsRequestDto.additionalCosts?.let { this.additionalCosts = it }
                financialDetailsRequestDto.deposit?.let { this.deposit = it }
            }
        }

        val updatedProperty = propertyRepository.save(property)
        return updatedProperty.toPropertyDto()
    }
}
