package com.windstation.aimodelingserver.dto.request

data class UpdateProjectDto(
    val projectId: Long,
    val name: String?,
    val description: String?,
)
