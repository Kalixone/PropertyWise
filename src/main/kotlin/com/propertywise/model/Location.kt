package com.propertywise.model

import jakarta.persistence.*

@Entity
data class Location(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long?,
    var address: String,
    var city: String,
    @Enumerated(EnumType.STRING)
    var province: Province,
    var postalCode: String,
    var neighborhood: String
)

enum class Province {
    DOLNOSLASKIE, KUJAWSKO_POMORSKIE, LUBELSKIE, LUBUSKIE, LODZKIE, MALOPOLSKIE,
    MAZOWIECKIE, OPOLSKIE, PODKARPACKIE, PODLASKIE, POMORSKIE, SLASKIE, SWIETOKRZYSKIE,
    WARMINSKO_MAZURSKIE, WIELKOPOLSKIE, ZACHODNIOPOMORSKIE
}
