package com.propertywise.service.impl

import com.propertywise.dto.CreatePropertyRequestDto
import com.propertywise.dto.PropertyDto
import com.propertywise.dto.PropertyPatchRequestDto
import com.propertywise.exceptions.PropertyNotFoundException
import com.propertywise.model.*
import com.propertywise.repository.PropertyRepository
import com.propertywise.repository.UserRepository
import com.propertywise.service.EmailService
import com.propertywise.service.PropertyService
import com.propertywise.toModel
import com.propertywise.toPropertyDto
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.security.core.Authentication
import org.springframework.stereotype.Service
import java.math.BigDecimal
import java.math.RoundingMode
import java.time.LocalDate
import java.time.LocalDateTime

@Service
class PropertyServiceImpl(
    private val propertyRepository: PropertyRepository,
    private val userRepository: UserRepository,
    private val emailService: EmailService)
    : PropertyService {

    override fun createProperty(createPropertyRequestDto: CreatePropertyRequestDto): PropertyDto {
        val propertyModel = createPropertyRequestDto.toModel()

            val price = propertyModel.price
            val area = propertyModel.area

            val areaBigDecimal = BigDecimal(area)
            val pricePerSquare = price.divide(areaBigDecimal, 2, RoundingMode.HALF_UP)

            propertyModel.pricePerSquareMeter = pricePerSquare.toDouble()

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
        return propertyRepository.findByTechnicalsDetailsNumberOfRooms(from, to)
            .map { property -> property.toPropertyDto() }.toList()
    }

    override fun findByAreaBetween(from: Double, to: Double): List<PropertyDto> {
        return propertyRepository.findByAreaBetween(from, to).map { property -> property.toPropertyDto() }.toList()
    }

    override fun findByType(type: Type): List<PropertyDto> {
        return propertyRepository.findByType(type).map { property -> property.toPropertyDto() }.toList()
    }

    override fun addToFavourites(id: Long, authentication: Authentication) {
        val principal = authentication.principal as User
        val property =
            propertyRepository.findById(id).orElseThrow { PropertyNotFoundException("Property with ID $id not found") }

        principal.favourites.add(property)
        userRepository.save(principal)
    }

    override fun getAllFavourites(authentication: Authentication): List<PropertyDto> {
        val principal = authentication.principal as User
        return principal.favourites.map { property -> property.toPropertyDto() }.toList()
    }

    @Scheduled(cron = "0 30 18 * * ?")
    override fun sendPropertyNotificationsToUser() {
        val users = userRepository.findAll()

        for (user in users) {
            val areaThreshold = user.areaThresholdForNotifications
            val priceThreshold = user.priceThresholdForNotifications
            val lastNotificationDate = user.lastNotificationDate ?: LocalDateTime.MIN

            val properties: List<Property> = propertyRepository.findAll().filter { property ->
                val createdDate = property.status.createdDate
                (createdDate.isAfter(lastNotificationDate.toLocalDate()) || property.status.lastUpdatedDate.isAfter(lastNotificationDate.toLocalDate())) &&
                        ((areaThreshold != null && property.area <= areaThreshold) ||
                                (priceThreshold != null && property.price <= priceThreshold))
            }

            val emailContent = if (properties.isNotEmpty()) {
                buildString {
                    append("<h1>New Properties matching your filters:</h1>")
                    for (property in properties) {
                        append("<p><strong>${property.title}</strong><br>")
                        append("Area: ${property.area} m²<br>")
                        append("Price: ${property.price}<br></p>")
                    }
                }
            } else {
                "Sorry, we could not find any properties matching your filters."
            }

            if (user.email.contains("@gmail.com")) {
                emailService.sendEmail(user.email,
                    if (properties.isNotEmpty()) "New Properties Based on Your Filters" else "No Properties Found Based on Your Filters",
                    emailContent, null)

                user.lastNotificationDate = LocalDateTime.now()
                userRepository.save(user)
            } else {
                println("Email not sent, user email does not contain @gmail.com")
            }
        }
    }

    override fun findBySaleOrRent(saleOrRent: SaleOrRent): List<PropertyDto> {
        return propertyRepository.findBySaleOrRent(saleOrRent).map { property -> property.toPropertyDto() }.toList()
    }

    override fun calculateAveragePriceForSquareMeter(province: Province): Double {
        val countByProvince = propertyRepository.findByProvince(province).count()

        if (countByProvince <= 0) {
            throw PropertyNotFoundException("No real estate in the province: $province")
        }

        val sum = propertyRepository.findByProvince(province).sumOf { property -> property.pricePerSquareMeter }

        return sum / countByProvince
    }

    override fun calculatePriceTrend(startYear: Int, endYear: Int, province: Province): Double {
        val propertiesStartYear = propertyRepository.findByProvinceAndYear(province, startYear)
        val propertiesEndYear = propertyRepository.findByProvinceAndYear(province, endYear)

        if (propertiesStartYear.isEmpty() || propertiesEndYear.isEmpty()) {
            throw PropertyNotFoundException("No properties found for the given years and province.")
        }

        val averagePriceStartYear = propertiesStartYear.map { it.pricePerSquareMeter }.average()

        val averagePriceEndYear = propertiesEndYear.map { it.pricePerSquareMeter }.average()

        val percentageChange = ((averagePriceEndYear - averagePriceStartYear) / averagePriceStartYear) * 100

        return percentageChange
    }
}
