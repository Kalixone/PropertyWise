package com.propertywise.service

import com.propertywise.dto.*
import com.propertywise.exceptions.RegistrationException
import org.springframework.security.core.Authentication
import org.springframework.stereotype.Service

@Service
interface UserService {
    @Throws(RegistrationException::class)
    fun register(requestDto: UserRegistrationRequestDto): UserResponseDto

    fun areaThresholdForNotifications(authentication: Authentication, requestDto: UserAreaRequestDto): UserDto

    fun priceThresholdForNotifications(authentication: Authentication, requestDto: UserPriceRequestDto): UserDto
}
