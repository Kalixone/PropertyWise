package com.propertywise.service.impl

import com.propertywise.dto.*
import com.propertywise.exceptions.RegistrationException
import com.propertywise.exceptions.RoleNotFoundException
import com.propertywise.exceptions.UnauthorizedAccessException
import com.propertywise.model.RoleName
import com.propertywise.model.User
import com.propertywise.repository.RoleRepository
import com.propertywise.repository.UserRepository
import com.propertywise.service.UserService
import com.propertywise.toUserDto
import com.propertywise.toUserResponseDto
import org.springframework.security.core.Authentication
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class UserServiceImpl(
    private val userRepository: UserRepository,
    private val passwordEncoder: PasswordEncoder,
    private val roleRepository: RoleRepository)
    : UserService {

    override fun register(requestDto: UserRegistrationRequestDto): UserResponseDto {
        val existingUser = userRepository.findByEmail(requestDto.email)
        if (existingUser != null) {
            throw RegistrationException("User with this email already exists")
        }

        val userRole = roleRepository.findByName(RoleName.USER)
            ?: throw RoleNotFoundException("Default role USER not found")
        val roles = setOf(userRole)

        val user = User(
            id = null,
            email = requestDto.email,
            firstName = requestDto.firstName,
            lastName = requestDto.lastName,
            password = passwordEncoder.encode(requestDto.password),
            favourites = mutableListOf(),
            roles = roles
        )

        val savedUser = userRepository.save(user)
        return savedUser.toUserResponseDto()
    }

    override fun areaThresholdForNotifications(authentication: Authentication, requestDto: UserAreaRequestDto): UserDto {
        val principal = authentication.principal as User
        principal.areaThresholdForNotifications = requestDto.area
        val user = userRepository.save(principal)
        return user.toUserDto()
    }

    override fun priceThresholdForNotifications(authentication: Authentication, requestDto: UserPriceRequestDto): UserDto {
        val principal = authentication.principal as User
        principal.priceThresholdForNotifications = requestDto.price
        val user = userRepository.save(principal)
        return user.toUserDto()
    }

    override fun deleteUser(id: Long, authentication: Authentication) {
        val principal = authentication.principal as User
        if (principal.roles.any { it.name == RoleName.ADMIN }) {
            userRepository.deleteById(id)
        } else {
            throw UnauthorizedAccessException("You do not have permission to delete the user.")
        }
    }
}
