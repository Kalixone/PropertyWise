package com.propertywise.model

import jakarta.persistence.*

@Entity
data class TechnicalDetails(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long?,
    val numberOfRooms: Int,
    val floor: Int,
    val totalFloors: Int,
    val yearBuild: Int,
    @Enumerated(EnumType.STRING)
    val heating: Heating,
    @Enumerated(EnumType.STRING)
    val constructionMaterial: ConstructionMaterial,
    @Enumerated(EnumType.STRING)
    val propertyCondition: PropertyCondition
)

enum class Heating() {
    GAS, ELECTRIC, CENTRAL
}

enum class ConstructionMaterial() {
    BRICK, WOOD, CONCRETE
}

enum class PropertyCondition() {
    FOR_RENOVATION, READY_TO_MOVE_IN
}
