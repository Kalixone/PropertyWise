package com.propertywise.service

import com.propertywise.dto.CreatePropertyRequestDto
import com.propertywise.dto.PropertyDto
import com.propertywise.dto.PropertyPatchRequestDto
import com.propertywise.model.Type
import org.springframework.security.core.Authentication
import org.springframework.stereotype.Service
import java.math.BigDecimal

@Service
interface PropertyService {
    fun createProperty(createPropertyRequestDto: CreatePropertyRequestDto) : PropertyDto

    fun getById(id: Long) : PropertyDto

    fun deleteById(id: Long)

    fun getAll() : List<PropertyDto>

    fun partialUpdate(id: Long, propertyPatchRequestDto: PropertyPatchRequestDto) : PropertyDto

    fun findByLocationCity(city: String) : List<PropertyDto>

    fun findByPriceBetween(from: BigDecimal, to: BigDecimal): List<PropertyDto>

    fun findByTechnicalsDetailsNumberOfRooms(from: Int, to: Int): List<PropertyDto>

    fun findByAreaBetween(from: Double, to: Double): List<PropertyDto>

    fun findByType(type: Type): List<PropertyDto>

    fun addToFavourites(id: Long, authentication: Authentication)

    fun getAllFavourites(authentication: Authentication): List<PropertyDto>

    fun sendPropertyNotificationsToUser()
}
