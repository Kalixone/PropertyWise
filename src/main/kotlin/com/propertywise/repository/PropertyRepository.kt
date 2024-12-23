package com.propertywise.repository

import com.propertywise.model.Property
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface PropertyRepository: JpaRepository<Property, Long>
