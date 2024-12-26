package com.propertywise.model

import jakarta.persistence.*
import java.time.LocalDate

@Entity
data class OfferStatus(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long?,
    @Enumerated(EnumType.STRING)
    var status: Status,
    var createdDate: LocalDate = LocalDate.now(),
    var lastUpdatedDate: LocalDate,
)

enum class Status {
    AVAILABLE, RESERVED, SOLD
}
