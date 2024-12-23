package com.propertywise.model

import jakarta.persistence.*

@Entity
data class Media(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long?,
    val photos: MutableList<String> = mutableListOf(),
    val videoLink: String
)
