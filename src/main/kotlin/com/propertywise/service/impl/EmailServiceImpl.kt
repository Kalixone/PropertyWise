package com.propertywise.service.impl

import com.propertywise.dto.EmailDto
import com.propertywise.model.Email
import com.propertywise.repository.EmailRepository
import com.propertywise.repository.UserRepository
import com.propertywise.service.EmailService
import com.propertywise.toEmailDto
import jakarta.mail.MessagingException
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.mail.javamail.MimeMessageHelper
import org.springframework.security.core.Authentication
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class EmailServiceImpl(
    private val javaMailSender: JavaMailSender,
    private val emailRepository: EmailRepository,
    private val userRepository: UserRepository):
    EmailService {

    override fun sendEmail(to: String, subject: String, text: String, authentication: Authentication?): EmailDto {
        val message = javaMailSender.createMimeMessage()

        return try {
            val helper = MimeMessageHelper(message, true)
            helper.setTo(to)
            helper.setSubject(subject)
            helper.setText(text, true)
            helper.setFrom("your-email@example.com")

            javaMailSender.send(message)

            val username = authentication?.name
            val appUser = username?.let { userRepository.findByEmail(it) }
                ?: throw RuntimeException("User not found for email: $username")

            val email = Email(
                id = null,
                sender = "your-email@example.com",
                recipient = to,
                subject = subject,
                content = text,
                sentAt = LocalDateTime.now(),
                user = appUser
            )

            emailRepository.save(email)
            email.toEmailDto()
        } catch (e: MessagingException) {
            throw RuntimeException("Error while sending email", e)
        }
    }
}
