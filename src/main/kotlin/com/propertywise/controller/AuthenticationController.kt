package com.propertywise.controller

import com.propertywise.dto.UserLoginRequestDto
import com.propertywise.dto.UserLoginResponseDto
import com.propertywise.dto.UserRegistrationRequestDto
import com.propertywise.dto.UserResponseDto
import com.propertywise.exceptions.RegistrationException
import com.propertywise.security.AuthenticationService
import com.propertywise.service.UserService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/auth")
@Tag(name = "Authentication", description = "APIs for user authentication and registration.")
class AuthenticationController(val userService: UserService, val authenticationService: AuthenticationService) {

    @PostMapping("/registration")
    @Throws(RegistrationException::class)
    @Operation(summary = "Register a new user", description = "Registers a new user with email, password, and name.")
    fun register(@RequestBody userRegistrationRequestDto: UserRegistrationRequestDto): UserResponseDto {
        return userService.register(userRegistrationRequestDto)
    }

    @PostMapping("/login")
    @Operation(summary = "Login user", description = "Authenticates a user and returns a login response with a token.")
    fun login(@RequestBody userLoginRequestDto: UserLoginRequestDto): UserLoginResponseDto {
        return authenticationService.authenticate(userLoginRequestDto)
    }
}
