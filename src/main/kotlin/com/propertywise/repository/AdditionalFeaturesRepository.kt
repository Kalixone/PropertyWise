package com.propertywise.repository

import com.propertywise.model.AdditionalFeatures
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface AdditionalFeaturesRepository: JpaRepository<AdditionalFeatures, Long>
