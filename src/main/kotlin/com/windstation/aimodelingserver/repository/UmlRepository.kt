package com.windstation.aimodelingserver.repository

import com.windstation.aimodelingserver.model.Uml
import org.springframework.data.jpa.repository.JpaRepository

interface UmlRepository : JpaRepository<Uml, Long> {

    fun findAllByProjectId(projectId: Long): List<Uml>

}