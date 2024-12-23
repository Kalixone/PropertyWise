package com.propertywise.dto

import com.propertywise.model.ConstructionMaterial
import com.propertywise.model.Heating
import com.propertywise.model.PropertyCondition

data class TechnicalDetailsDto(val id: Long, val numberOfRooms: Int, val floor: Int, val totalFloors: Int, val yearBuild: Int, val heating: Heating, val constructionMaterial: ConstructionMaterial, val propertyCondition: PropertyCondition)
