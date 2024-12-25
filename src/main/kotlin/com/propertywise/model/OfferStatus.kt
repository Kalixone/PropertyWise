package com.propertywise.model

import jakarta.persistence.*
import java.time.LocalDate

@Entity
data class OfferStatus(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long?,
    @Enumerated(EnumType.STRING)
    val status: Status,
    val createdDate: LocalDate,
    val lastUpdatedDate: LocalDate,
    // val name: String <- nazwa uzytkoniwka np imie i nazwisko osoby ktora to dodała te oferte
)

enum class Status {
    AVAILABLE, RESERVED, SOLD
}
