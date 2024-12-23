package com.propertywise.repository

import com.propertywise.model.Media
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface MediaRepository: JpaRepository<Media, Long>
