package com.propertywise.service

import com.propertywise.dto.UserRegistrationRequestDto
import com.propertywise.dto.UserResponseDto
import com.propertywise.exceptions.RegistrationException
import org.springframework.stereotype.Service

@Service
interface UserService {
    @Throws(RegistrationException::class)
    fun register(requestDto: UserRegistrationRequestDto): UserResponseDto
}
