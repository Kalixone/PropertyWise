package com.propertywise.repository

import com.propertywise.model.OfferStatus
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface OfferStatusRepository: JpaRepository<OfferStatus, Long>
