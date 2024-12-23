package com.propertywise.model

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id

@Entity
data class Location(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long?,
    val address: String,
    val city: String,
    val postalCode: String,
    val neighborhood: String
    // latitude longtitude in future
)
