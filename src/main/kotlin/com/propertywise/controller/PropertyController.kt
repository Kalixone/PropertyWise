package com.propertywise.controller

import com.propertywise.dto.CreatePropertyRequestDto
import com.propertywise.dto.PropertyDto
import com.propertywise.dto.PropertyPatchRequestDto
import com.propertywise.model.Type
import com.propertywise.service.PropertyService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.security.core.Authentication
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.math.BigDecimal

@RestController
@RequestMapping("/api/property")
@Tag(name = "Property Management", description = "APIs for managing properties.")
class PropertyController(private val propertyService: PropertyService) {

    @PostMapping
    @PreAuthorize("hasAuthority('USER')")
    @Operation(summary = "Create a new property", description = "Creates a new property.")
    fun createProperty(@RequestBody createPropertyRequestDto: CreatePropertyRequestDto) : PropertyDto {
        return propertyService.createProperty(createPropertyRequestDto)
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('USER')")
    @Operation(summary = "Get property by ID", description = "Fetches a property by its unique identifier.")
    fun getById(@PathVariable id: Long) : PropertyDto {
        return propertyService.getById(id)
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('USER')")
    @Operation(summary = "Delete property by ID", description = "Deletes a property by its unique identifier.")
    fun deleteById(@PathVariable id: Long) {
        propertyService.deleteById(id)
    }

    @GetMapping
    @PreAuthorize("hasAuthority('USER')")
    @Operation(summary = "Get all properties", description = "Fetches a list of all properties.")
    fun getAll() : List<PropertyDto> {
        return propertyService.getAll()
    }

    @PatchMapping("/{id}")
    @PreAuthorize("hasAuthority('USER')")
    @Operation(summary = "Partially update property", description = "Updates specific fields of a property.")
    fun partialUpdate(@PathVariable id: Long, @RequestBody propertyPatchRequestDto: PropertyPatchRequestDto) : PropertyDto {
        return propertyService.partialUpdate(id, propertyPatchRequestDto)
    }

    @GetMapping("/city")
    @PreAuthorize("hasAuthority('USER')")
    @Operation(summary = "Find properties by city", description = "Retrieve properties located in the specified city.")
    fun findByLocationCity(@RequestParam city: String) : List<PropertyDto> {
        return propertyService.findByLocationCity(city)
    }

    @GetMapping("/price")
    @PreAuthorize("hasAuthority('USER')")
    @Operation(summary = "Find properties by price range", description = "Retrieve properties within the specified price range.")
    fun findByPriceBetween(@RequestParam from: BigDecimal, @RequestParam to: BigDecimal): List<PropertyDto> {
        return propertyService.findByPriceBetween(from, to)
    }

    @GetMapping("/rooms")
    @PreAuthorize("hasAuthority('USER')")
    @Operation(summary = "Find properties by number of rooms", description = "Retrieves properties within a specified number of rooms range.")
    fun findByTechnicalsDetailsNumberOfRooms(@RequestParam from: Int, @RequestParam to: Int): List<PropertyDto> {
        return propertyService.findByTechnicalsDetailsNumberOfRooms(from,to)
    }

    @GetMapping("/area")
    @PreAuthorize("hasAuthority('USER')")
    @Operation(summary = "Find properties by area range", description = "Retrieves properties within a specified area range.")
    fun findByAreaBetween(@RequestParam from: Double, @RequestParam to: Double): List<PropertyDto> {
        return propertyService.findByAreaBetween(from, to)
    }

    @GetMapping("/type")
    @PreAuthorize("hasAuthority('USER')")
    @Operation(summary = "Find properties by type", description = "Allows users to retrieve a list of properties filtered by their type, such as APARTMENT, RESIDENCE, or PLOT.")
    fun findByType(@RequestParam type: Type): List<PropertyDto> {
        return propertyService.findByType(type)
    }

    @PostMapping("/favourites/{id}")
    @PreAuthorize("hasAuthority('USER')")
    @Operation(summary = "Add a property to the user's favourites", description = "Allows a user to add a property to their favourites list. The user must be authenticated and have the 'USER' role.")
    fun addToFavourites(@PathVariable id: Long, authentication: Authentication) {
        return propertyService.addToFavourites(id, authentication)
    }

    @GetMapping("/favourites")
    @PreAuthorize("hasAuthority('USER')")
    @Operation(summary = "Get all properties from the user's favourites", description = "Allows a user to retrieve a list of all properties that have been added to their favourites. The user must be authenticated and have the 'USER' role.")
    fun getAllFavourites(authentication: Authentication): List<PropertyDto> {
        return propertyService.getAllFavourites(authentication)
    }
 }
