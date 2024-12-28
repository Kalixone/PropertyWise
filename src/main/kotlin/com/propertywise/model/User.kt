package com.propertywise.model

import jakarta.persistence.*
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import java.math.BigDecimal
import java.time.LocalDateTime

@Entity
data class User(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long?,
    val email: String,
    private val password: String,
    val firstName: String,
    val lastName: String,
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "user_roles",
        joinColumns = [JoinColumn(name = "user_id")],
        inverseJoinColumns = [JoinColumn(name = "role_id")]
    )
    val roles: Set<Role>,
    @OneToMany(fetch = FetchType.EAGER)
    val favourites: MutableList<Property>,
    var areaThresholdForNotifications: Double? = null,
    var priceThresholdForNotifications: BigDecimal? = null,
    @Column(nullable = true)
    var lastNotificationDate: LocalDateTime? = null

): UserDetails {
    override fun getAuthorities(): Collection<GrantedAuthority?>? {
        return roles
    }

    override fun getPassword(): String {
        return password
    }

    override fun getUsername(): String {
        return email
    }

    override fun isAccountNonExpired(): Boolean {
        return true
    }

    override fun isAccountNonLocked(): Boolean {
        return true
    }

    override fun isCredentialsNonExpired(): Boolean {
        return true
    }
}
