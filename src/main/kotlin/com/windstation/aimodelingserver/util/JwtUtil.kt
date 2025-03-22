package com.windstation.aimodelingserver.util

import com.windstation.aimodelingserver.dto.response.TokenResponseDto
import io.jsonwebtoken.Claims
import io.jsonwebtoken.JwtException
import io.jsonwebtoken.Jwts
import org.springframework.stereotype.Component
import java.util.Date
import javax.crypto.SecretKey

@Component
class JwtUtil {

    val secretKey: SecretKey = Jwts.SIG.HS256.key().build()
    val expireTime = 2520000L
    val refreshExpireTime = 86400000L

    fun buildToken(subject: String, expire: Long): String {
        return Jwts.builder()
            .subject(subject)
            .expiration(Date(System.currentTimeMillis() + expire))
            .issuedAt(Date())
            .signWith(secretKey)
            .compact()
    }

    fun generateToken(account: String): TokenResponseDto {
        return TokenResponseDto(
            accessToken = buildToken(account, expireTime),
            refreshToken = buildToken(account, refreshExpireTime),
        )
    }

    private fun extractClaims(token: String): Claims {
        return Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token).payload
    }

    fun extractUsername(token: String): String {
        return extractClaims(token).subject
    }

    fun isExpired(token: String): Boolean {
        val claims = extractClaims(token)
        return claims.expiration.before(Date())
    }

}