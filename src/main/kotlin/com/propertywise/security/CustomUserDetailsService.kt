package com.propertywise.security

import com.propertywise.exceptions.UsernameNotFoundException
import com.propertywise.repository.UserRepository
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service

@Service
class CustomUserDetailsService(
    private val userRepository: UserRepository)
    : UserDetailsService {

    override fun loadUserByUsername(email: String): UserDetails {
        return userRepository.findByEmail(email)
            ?: throw UsernameNotFoundException("Can't find user by email")
    }
}
