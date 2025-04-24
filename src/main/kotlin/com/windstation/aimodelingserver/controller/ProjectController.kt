package com.windstation.aimodelingserver.controller

import com.windstation.aimodelingserver.dto.request.CreateProjectDto
import com.windstation.aimodelingserver.dto.request.UpdateProjectDto
import com.windstation.aimodelingserver.model.Project
import com.windstation.aimodelingserver.model.User
import com.windstation.aimodelingserver.services.ProjectService
import io.swagger.v3.oas.annotations.security.SecurityRequirement
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.ResponseEntity
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.*

@RestController
@SecurityRequirement(name = "BearerAuth")
@Tag(name = "project", description = "与用户UML工程相关的API")
@RequestMapping("/api")
class ProjectController(private val projectService: ProjectService) {

    @GetMapping("/self/projects")
    fun getSelfProjects(@AuthenticationPrincipal user: User): ResponseEntity<List<Project>> {
        return ResponseEntity.ok(projectService.findAllByUserId(user.id))
    }

    @GetMapping("/project/{id}")
    fun getProject(@PathVariable id: Long): ResponseEntity<Project> {
        return ResponseEntity.ok(projectService.findProjectById(id))
    }

    @PostMapping("/self/project")
    fun createProjectForSelf(@RequestBody dto: CreateProjectDto, @AuthenticationPrincipal user: User): ResponseEntity<Project> {
        return ResponseEntity.ok(projectService.createProject(dto, user.id))
    }

    @DeleteMapping("/project/{id}")
    fun deleteProject(@PathVariable("id") id: Long): ResponseEntity<Void> {
        projectService.deleteProject(id)
        return ResponseEntity.ok().build()
    }

    @PutMapping("/project/update")
    fun updateProject(@RequestBody dto: UpdateProjectDto): ResponseEntity<Project> {
        return ResponseEntity.ok(projectService.updateProject(dto))
    }

}