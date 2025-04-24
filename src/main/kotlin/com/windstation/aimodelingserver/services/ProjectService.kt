package com.windstation.aimodelingserver.services

import com.windstation.aimodelingserver.dto.request.CreateProjectDto
import com.windstation.aimodelingserver.dto.request.UpdateProjectDto
import com.windstation.aimodelingserver.model.Project
import com.windstation.aimodelingserver.repository.ProjectRepository
import com.windstation.aimodelingserver.repository.UserRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Service
@Transactional
class ProjectService(
    private val projectRepository: ProjectRepository,
    private val userRepository: UserRepository
) {

    fun createProject(dto: CreateProjectDto, userId: Long): Project {
        val user = userRepository.findById(userId).orElseThrow()
        return projectRepository.save(Project(
            name = dto.title,
            description = dto.description,
            user = user
        ))
    }

    fun findAllByUserId(userId: Long): List<Project> {
        return projectRepository.findAllByUserId(userId)
    }

    fun findProjectById(id: Long): Project {
        return projectRepository.findById(id).orElseThrow()
    }

    fun updateProject(dto: UpdateProjectDto): Project {
        val project = projectRepository.findById(dto.projectId).orElseThrow()

        if (!dto.name.isNullOrBlank()) {
            project.name = dto.name
        }

        if (!dto.description.isNullOrBlank()) {
            project.description = dto.description
        }

        return projectRepository.save(project)
    }

    fun deleteProject(id: Long) {
        projectRepository.deleteById(id)
    }

}