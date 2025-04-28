package com.windstation.aimodelingserver.services

import com.windstation.aimodelingserver.model.Chat
import com.windstation.aimodelingserver.model.User
import com.windstation.aimodelingserver.repository.ChatRepository
import com.windstation.aimodelingserver.repository.UserRepository
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class UserService(
    private val userRepository: UserRepository,
) : UserDetailsService {

    override fun loadUserByUsername(username: String?): UserDetails {
        return userRepository.findByAccount(username!!).orElseThrow {
            UsernameNotFoundException("User $username not found")
        }
    }

    fun findById(userId: Long): User {
        return userRepository.findById(userId).orElseThrow()
    }

}