package com.propertywise.dto

import com.propertywise.model.ConstructionMaterial
import com.propertywise.model.Heating
import com.propertywise.model.PropertyCondition

data class TechnicalDetailsPatchRequestDto(
    val numberOfRooms: Int? = null,
    val floor: Int? = null,
    val totalFloors: Int? = null,
    val yearBuild: Int? = null,
    val heating: Heating? = null,
    val constructionMaterial: ConstructionMaterial? = null,
    val propertyCondition: PropertyCondition? = null
)
