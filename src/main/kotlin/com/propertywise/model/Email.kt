package com.propertywise.model

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.ManyToOne
import java.time.LocalDateTime

@Entity
data class Email(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long?,
    val sender: String,
    val recipient: String,
    val subject: String,
    val content: String,
    val sentAt: LocalDateTime,
    @ManyToOne
    val user: User
)
