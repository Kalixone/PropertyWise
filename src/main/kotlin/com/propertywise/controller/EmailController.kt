package com.propertywise.controller

import com.propertywise.dto.EmailDto
import com.propertywise.service.EmailService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.security.core.Authentication
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("api/email")
@Tag(name = "Email Managment", description = "Endpoints for sending emails related to property notifications and user interactions.")
class EmailController(private val emailService: EmailService) {

    @PostMapping
    @PreAuthorize("hasAuthority('USER')")
    @Operation(summary = "Send email", description = "Allows the user to send an email with the specified recipient, subject, and content.")
    fun sendEmail(to: String, subject: String, text: String, authentication: Authentication): EmailDto {
        return emailService.sendEmail(to, subject, text, authentication)
    }
}
