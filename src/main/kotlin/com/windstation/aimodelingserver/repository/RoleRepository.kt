package com.windstation.aimodelingserver.repository

import com.windstation.aimodelingserver.model.Role
import org.springframework.data.jpa.repository.JpaRepository
import java.util.Optional

interface RoleRepository : JpaRepository<Role, Long> {
    fun findByName(name: String): Optional<Role>
}