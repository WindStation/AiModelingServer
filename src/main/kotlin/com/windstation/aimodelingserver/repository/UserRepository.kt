package com.windstation.aimodelingserver.repository

import com.windstation.aimodelingserver.model.User
import org.springframework.data.jpa.repository.JpaRepository
import java.util.Optional

interface UserRepository : JpaRepository<User, Long> {
    fun findByAccount(account: String): Optional<User>
}