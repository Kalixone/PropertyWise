package com.propertywise.security

import com.propertywise.dto.UserLoginRequestDto
import com.propertywise.dto.UserLoginResponseDto
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.stereotype.Service

@Service
class AuthenticationService(
    private val jwtUtil: JwtUtil,
    private val authenticationManager: AuthenticationManager
) {

    fun authenticate(request: UserLoginRequestDto): UserLoginResponseDto {
        val authenticate: Authentication = authenticationManager.authenticate(
            UsernamePasswordAuthenticationToken(request.email, request.password)
        )
        val token = jwtUtil.generateToken(authenticate.name)
        return UserLoginResponseDto(token)
    }
}
