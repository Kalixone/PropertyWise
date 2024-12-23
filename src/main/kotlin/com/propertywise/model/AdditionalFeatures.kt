package com.propertywise.model

import jakarta.persistence.*

@Entity
data class AdditionalFeatures(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long?,
    val balcony: Boolean,
    val garden: Boolean,
    val garage: Boolean,
    val elevator: Boolean,
    val furnished: Boolean
)
