package com.propertywise.repository

import com.propertywise.model.Property
import com.propertywise.model.SaleOrRent
import com.propertywise.model.Type
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import java.math.BigDecimal

@Repository
interface PropertyRepository: JpaRepository<Property, Long> {
    fun findByLocationCity(city: String): List<Property>

    fun findByPriceBetween(from: BigDecimal, to: BigDecimal): List<Property>

    @Query("SELECT p FROM Property p WHERE p.technicalsDetails.numberOfRooms BETWEEN :from AND :to")
    fun findByTechnicalsDetailsNumberOfRooms(@Param("from") from: Int, @Param("to") to: Int): List<Property>

    fun findByAreaBetween(from: Double, to: Double): List<Property>

    fun findByType(type: Type): List<Property>

    @Query("SELECT p FROM Property p WHERE p.saleOrRent = :saleOrRent")
    fun findBySaleOrRent(@Param("saleOrRent") saleOrRent: SaleOrRent): List<Property>
}