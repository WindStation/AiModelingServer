package com.windstation.aimodelingserver.services

import com.windstation.aimodelingserver.dto.request.RegisterRequestDto
import com.windstation.aimodelingserver.dto.response.TokenResponseDto
import com.windstation.aimodelingserver.enums.RoleEnum
import com.windstation.aimodelingserver.model.User
import com.windstation.aimodelingserver.repository.RoleRepository
import com.windstation.aimodelingserver.repository.UserRepository
import com.windstation.aimodelingserver.util.JwtUtil
import org.springframework.dao.DataIntegrityViolationException
import org.springframework.dao.DuplicateKeyException
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class AuthenticationService(
    private val userRepository: UserRepository,
    private val authenticationManager: AuthenticationManager,
    private val jwtUtil: JwtUtil,
    private val passwordEncoder: PasswordEncoder,
    private val roleRepository: RoleRepository
) {


    fun authenticate(username: String, password: String): TokenResponseDto {
        val authentication = authenticationManager.authenticate(
            UsernamePasswordAuthenticationToken(username, password)
        )
        val user = authentication.principal as User
        return jwtUtil.generateToken(user.username)
    }

    fun register(dto: RegisterRequestDto): TokenResponseDto {
        val userRole = roleRepository.findByName("ROLE_${RoleEnum.USER.name}").orElseThrow()
        val user = User(
            account = dto.account,
            password = passwordEncoder.encode(dto.password),
            roles = mutableSetOf(userRole)
        )
        try {
            userRepository.save(user)
        } catch (e: DataIntegrityViolationException) {
            throw Exception("User account duplicated", e)
        }

        return jwtUtil.generateToken(user.username)
    }

//    fun refresh(refreshToken: String): TokenResponseDto {
//
//    }
}