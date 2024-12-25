package com.propertywise.model

import jakarta.persistence.*

@Entity
data class TechnicalDetails(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long?,
    var numberOfRooms: Int,
    var floor: Int,
    var totalFloors: Int,
    var yearBuild: Int,
    @Enumerated(EnumType.STRING)
    var heating: Heating,
    @Enumerated(EnumType.STRING)
    var constructionMaterial: ConstructionMaterial,
    @Enumerated(EnumType.STRING)
    var propertyCondition: PropertyCondition
)

enum class Heating {
    GAS, ELECTRIC, CENTRAL
}

enum class ConstructionMaterial {
    BRICK, WOOD, CONCRETE
}

enum class PropertyCondition {
    FOR_RENOVATION, READY_TO_MOVE_IN
}
