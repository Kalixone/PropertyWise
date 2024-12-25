package com.propertywise.dto

data class UserRegistrationRequestDto(val email: String, val password: String, val repeatPassword: String, val firstName: String, val lastName: String)
