package com.propertywise.model

import jakarta.persistence.*

@Entity
data class AdditionalFeatures(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long?,
    var balcony: Boolean,
    var garden: Boolean,
    var garage: Boolean,
    var elevator: Boolean,
    var furnished: Boolean
)
