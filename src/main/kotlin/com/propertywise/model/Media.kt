package com.propertywise.model

import jakarta.persistence.*

@Entity
data class Media(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long?,
    var photos: MutableList<String> = mutableListOf(),
    var videoLink: String
)
