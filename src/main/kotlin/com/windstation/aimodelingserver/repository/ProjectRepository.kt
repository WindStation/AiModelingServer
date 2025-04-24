package com.windstation.aimodelingserver.repository

import com.windstation.aimodelingserver.model.Project
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import java.util.*

interface ProjectRepository : JpaRepository<Project, Long> {
    @Query("SELECT p FROM Project p LEFT JOIN FETCH p.umls WHERE p.user.id = :userId")
    fun findAllByUserId(userId: Long): List<Project>
}