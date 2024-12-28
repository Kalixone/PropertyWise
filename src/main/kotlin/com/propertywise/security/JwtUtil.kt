package com.propertywise.security

import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jws
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.security.Keys
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import java.nio.charset.StandardCharsets
import java.security.Key
import java.util.*

@Component
class JwtUtil(
    @Value("\${jwt.secret}") secretString: String) {
    private val secret: Key = Keys.hmacShaKeyFor(secretString.toByteArray(StandardCharsets.UTF_8))

    @Value("\${jwt.expiration}")
    private var expiration: Long = 0

    fun generateToken(username: String): String {
        return Jwts.builder()
            .setSubject(username)
            .setIssuedAt(Date(System.currentTimeMillis()))
            .setExpiration(Date(System.currentTimeMillis() + expiration))
            .signWith(secret)
            .compact()
    }

    fun isValidToken(token: String): Boolean {
        val claimsJws: Jws<Claims> = Jwts.parserBuilder()
            .setSigningKey(secret)
            .build()
            .parseClaimsJws(token)
        return !claimsJws.body.expiration.before(Date())
    }

    fun getUserName(token: String): String {
        return getClaimFromToken(token) { claims: Claims -> claims.subject }
    }

    private fun <T> getClaimFromToken(token: String, claimsResolver: (Claims) -> T): T {
        val claims: Claims = Jwts.parserBuilder()
            .setSigningKey(secret)
            .build()
            .parseClaimsJws(token)
            .body
        return claimsResolver(claims)
    }
}
