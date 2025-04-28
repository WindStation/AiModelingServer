package com.windstation.aimodelingserver.controller

import com.windstation.aimodelingserver.dto.request.LoginRequestDto
import com.windstation.aimodelingserver.dto.request.RegisterRequestDto
import com.windstation.aimodelingserver.dto.response.TokenResponseDto
import com.windstation.aimodelingserver.services.AuthenticationService
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/auth")
@Tag(name = "Auth", description = "用户鉴权和注册接口")
class AuthController(
    private val authenticationService: AuthenticationService
) {

    @PostMapping("/login")
    fun login(@RequestBody dto: LoginRequestDto): ResponseEntity<TokenResponseDto> {
        return ResponseEntity.ok(authenticationService.authenticate(dto.username, dto.password))
    }

    @PostMapping("/register")
    fun register(@RequestBody dto: RegisterRequestDto): ResponseEntity<TokenResponseDto> {
        return ResponseEntity.ok(authenticationService.register(dto))
    }

    @PostMapping("/refresh")
    fun refresh(@RequestBody refreshToken: String): ResponseEntity<TokenResponseDto> {
        return ResponseEntity.ok(authenticationService.refresh(refreshToken))
    }

}