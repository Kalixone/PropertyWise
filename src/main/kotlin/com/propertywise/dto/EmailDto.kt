package com.propertywise.dto

import java.time.LocalDateTime

data class EmailDto(val id: Long, val sender: String, val recipient: String, val subject: String, val content: String, val sentAt: LocalDateTime)
