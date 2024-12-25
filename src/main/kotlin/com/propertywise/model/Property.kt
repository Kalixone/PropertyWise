package com.propertywise.model

import jakarta.persistence.*
import java.math.BigDecimal

@Entity
data class Property(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long?,
    var title: String,
    var description: String,
    var price: BigDecimal,
    var pricePerSquareMeter: Double,
    var area: Double,
    @Enumerated(EnumType.STRING)
    var type: Type,
    @OneToOne(cascade = [CascadeType.ALL])
    val location: Location,
    @OneToOne(cascade = [CascadeType.ALL])
    val technicalsDetails: TechnicalDetails,
    @OneToOne(cascade = [CascadeType.ALL])
    val additionalFeatures: AdditionalFeatures,
    @OneToOne(cascade = [CascadeType.ALL])
    val media: Media,
    @OneToOne(cascade = [CascadeType.ALL])
    val status: OfferStatus,
    @OneToOne(cascade = [CascadeType.ALL])
    val financialDetails: FinancialDetails)

enum class Type {
    APARTMENT, RESIDENCE, PLOT
}
