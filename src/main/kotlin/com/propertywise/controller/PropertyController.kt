package com.propertywise.controller

import com.propertywise.dto.CreatePropertyRequestDto
import com.propertywise.dto.PropertyDto
import com.propertywise.dto.PropertyPatchRequestDto
import com.propertywise.service.PropertyService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

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
}
