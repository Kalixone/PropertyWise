package com.propertywise

import com.propertywise.dto.*
import com.propertywise.model.*

fun Property.toPropertyDto(): PropertyDto {
    return PropertyDto(
        id=this.id ?: 0,
        title=this.title,
        description=this.description,
        price=this.price,
        pricePerSquareMeter=this.pricePerSquareMeter,
        type=this.type,
        location=this.location.toLocationDto(),
        technicalDetails=this.technicalsDetails.toTechnicalDetailsDto(),
        additionalFeatures=this.additionalFeatures.toAdditionalFeaturesDto(),
        media=this.media.toMediaDto(),
        status=this.status.toOfferStatusDto(),
        financialDetails=this.financialDetails.toFinancialDetailsDto()
    )
}

fun Location.toLocationDto(): LocationDto {
    return LocationDto(
        id=this.id ?: 0,
        address=this.address,
        city=this.city,
        postalCode=this.postalCode,
        neighborhood=this.neighborhood
    )
}

fun TechnicalDetails.toTechnicalDetailsDto(): TechnicalDetailsDto {
    return TechnicalDetailsDto(
        id=this.id ?: 0,
        numberOfRooms=this.numberOfRooms,
        floor=this.floor,
        totalFloors=this.totalFloors,
        yearBuild=this.yearBuild,
        heating=this.heating,
        constructionMaterial=this.constructionMaterial,
        propertyCondition=this.propertyCondition
    )
}

fun AdditionalFeatures.toAdditionalFeaturesDto(): AdditionalFeaturesDto {
    return AdditionalFeaturesDto(
        id=this.id ?: 0,
        balcony=this.balcony,
        garden=this.garden,
        garage=this.garage,
        elevator=this.elevator,
        furnished=this.furnished
    )
}

fun Media.toMediaDto(): MediaDto {
    return MediaDto(
        id=this.id ?: 0,
        photos=this.photos,
        videoLink=this.videoLink
    )
}

fun OfferStatus.toOfferStatusDto(): OfferStatusDto {
    return OfferStatusDto(
        id=this.id ?: 0,
        status=this.status,
        createdDate=this.createdDate,
        lastUpdatedDate=this.lastUpdatedDate
    )
}

fun FinancialDetails.toFinancialDetailsDto(): FinancialDetailsDto {
    return FinancialDetailsDto(
        id=this.id ?: 0,
        monthlyRent=this.monthlyRent,
        additionalCosts=this.additionalCosts,
        deposit=this.deposit
    )
}

fun CreatePropertyRequestDto.toModel(): Property {
    return Property(
        id=null,
        title=this.title,
        description=this.description,
        price=this.price,
        pricePerSquareMeter=this.pricePerSquareMeter,
        area=this.area,
        type=this.type,
        location=this.location.toLocation(),
        technicalsDetails=this.technicalDetails.toTechnicalDetails(),
        additionalFeatures=this.additionalFeatures.toAdditionalFeatures(),
        media=this.media.toMedia(),
        status=this.offerStatus.toOfferStatus(),
        financialDetails=this.financialDetails.toFinancialDetails()
    )
}

fun CreateLocationRequestDto.toLocation(): Location {
    return Location(
        id=null,
        address=this.address,
        city=this.city,
        postalCode=this.postalCode,
        neighborhood=this.neighborhood
    )
}

fun CreateTechnicalDetailsRequestDto.toTechnicalDetails(): TechnicalDetails {
    return TechnicalDetails(
        id=null,
        numberOfRooms=this.numberOfRooms,
        floor=this.floor,
        totalFloors=this.totalFloors,
        yearBuild=this.yearBuild,
        heating=this.heating,
        constructionMaterial=this.constructionMaterial,
        propertyCondition=this.propertyCondition
    )
}

fun CreateAdditionalFeaturesRequestDto.toAdditionalFeatures(): AdditionalFeatures {
    return AdditionalFeatures(
        id=null,
        balcony=this.balcony,
        garden=this.garden,
        garage=this.garage,
        elevator=this.elevator,
        furnished=this.furnished
    )
}

fun CreateOfferStatusRequestDto.toOfferStatus(): OfferStatus {
    return OfferStatus(
        id= null,
        status=this.status,
        createdDate=this.createdDate,
        lastUpdatedDate=this.lastUpdatedDate
    )
}

fun CreateFinancialDetailsRequestDto.toFinancialDetails(): FinancialDetails {
    return FinancialDetails(
        id=null,
        monthlyRent=this.monthlyRent,
        additionalCosts=this.additionalCosts,
        deposit=this.deposit
    )
}

fun CreateMediaRequestDto.toMedia(): Media {
    return Media(
        id=null,
        photos=this.photos,
        videoLink=this.videoLink
    )
}
