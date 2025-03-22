package com.windstation.aimodelingserver.filter

import com.windstation.aimodelingserver.services.UserService
import com.windstation.aimodelingserver.util.JwtUtil
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter

@Component
class JwtFilter(
    private val jwtUtil: JwtUtil,
    private val userService: UserService,
) : OncePerRequestFilter() {
    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        val authHeader = request.getHeader("Authorization")

        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            val authToken = authHeader.substring(7)
            val account: String
            try {
                account = jwtUtil.extractUsername(authToken)
            } catch (e: Exception) {
                SecurityContextHolder.clearContext()
                filterChain.doFilter(request, response)
                return
            }

            val user = userService.loadUserByUsername(account)

            val authentication = UsernamePasswordAuthenticationToken(user, null, user.authorities)
            SecurityContextHolder.getContext().authentication = authentication
        } else {
            SecurityContextHolder.clearContext()
        }
        filterChain.doFilter(request, response)
    }
}