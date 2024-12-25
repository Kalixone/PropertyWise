package com.propertywise.model

import jakarta.persistence.*
import org.springframework.security.core.GrantedAuthority

@Entity
data class Role(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long?,
    @Enumerated(EnumType.STRING)
    val name: RoleName
): GrantedAuthority {
    override fun getAuthority(): String {
        return name.name
    }
}

enum class RoleName {
    USER, ADMIN
}
