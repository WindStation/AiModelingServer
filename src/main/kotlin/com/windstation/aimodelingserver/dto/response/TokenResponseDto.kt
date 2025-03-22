package com.windstation.aimodelingserver.dto.response

data class TokenResponseDto(
    val accessToken: String,
    val refreshToken: String,
)
