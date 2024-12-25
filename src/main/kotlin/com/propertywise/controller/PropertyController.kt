package com.propertywise.controller

import com.propertywise.dto.CreatePropertyRequestDto
import com.propertywise.dto.PropertyDto
import com.propertywise.dto.PropertyPatchRequestDto
import com.propertywise.service.PropertyService
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
class PropertyController(private val propertyService: PropertyService) {

    @PostMapping
    fun createProperty(@RequestBody createPropertyRequestDto: CreatePropertyRequestDto) : PropertyDto {
        return propertyService.createProperty(createPropertyRequestDto)
    }

    @GetMapping("/{id}")
    fun getById(@PathVariable id: Long) : PropertyDto {
        return propertyService.getById(id)
    }

    @DeleteMapping("/{id}")
    fun deleteById(@PathVariable id: Long) {
        propertyService.deleteById(id)
    }

    @GetMapping
    fun getAll() : List<PropertyDto> {
        return propertyService.getAll()
    }

    @PatchMapping("/{id}")
    fun partialUpdate(@PathVariable id: Long, @RequestBody propertyPatchRequestDto: PropertyPatchRequestDto) : PropertyDto {
        return propertyService.partialUpdate(id, propertyPatchRequestDto)
    }
}
