package com.propertywise.service

import com.propertywise.dto.EmailDto
import org.springframework.security.core.Authentication
import org.springframework.stereotype.Service

@Service
interface EmailService {
    fun sendEmail(to: String, subject: String, text: String, authentication: Authentication?): EmailDto
}
