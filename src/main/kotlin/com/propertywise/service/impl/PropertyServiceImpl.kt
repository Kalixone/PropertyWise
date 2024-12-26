package com.propertywise.service.impl

import com.propertywise.dto.CreatePropertyRequestDto
import com.propertywise.dto.PropertyDto
import com.propertywise.dto.PropertyPatchRequestDto
import com.propertywise.exceptions.PropertyNotFoundException
import com.propertywise.model.Property
import com.propertywise.model.Type
import com.propertywise.model.User
import com.propertywise.repository.PropertyRepository
import com.propertywise.repository.UserRepository
import com.propertywise.service.PropertyService
import com.propertywise.toModel
import com.propertywise.toPropertyDto
import org.springframework.security.core.Authentication
import org.springframework.stereotype.Service
import java.math.BigDecimal
import java.time.LocalDate

@Service
class PropertyServiceImpl(
    private val propertyRepository: PropertyRepository, private val userRepository: UserRepository)
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
        updatedProperty.status.lastUpdatedDate = LocalDate.now()
        return updatedProperty.toPropertyDto()
    }

    override fun findByLocationCity(city: String): List<PropertyDto> {
        return propertyRepository.findByLocationCity(city).map { property -> property.toPropertyDto() }.toList()
    }

    override fun findByPriceBetween(from: BigDecimal, to: BigDecimal): List<PropertyDto> {
        return propertyRepository.findByPriceBetween(from, to).map { property -> property.toPropertyDto() }.toList()
    }

    override fun findByTechnicalsDetailsNumberOfRooms(from: Int, to: Int): List<PropertyDto> {
        return propertyRepository.findByTechnicalsDetailsNumberOfRooms(from, to).map { property -> property.toPropertyDto() }.toList()
    }

    override fun findByAreaBetween(from: Double, to: Double): List<PropertyDto> {
        return propertyRepository.findByAreaBetween(from, to).map { property -> property.toPropertyDto() }.toList()
    }

    override fun findByType(type: Type): List<PropertyDto> {
        return propertyRepository.findByType(type).map { property -> property.toPropertyDto() }.toList()
    }

    override fun addToFavourites(id: Long, authentication: Authentication) {
       val principal = authentication.principal as User
       val property = propertyRepository.findById(id).orElseThrow { PropertyNotFoundException("Property with ID $id not found")}

        principal.favourites.add(property)
        userRepository.save(principal)
    }

    override fun getAllFavourites(authentication: Authentication): List<PropertyDto> {
        val principal = authentication.principal as User
        return principal.favourites.map { property -> property.toPropertyDto() }.toList()
    }
}
