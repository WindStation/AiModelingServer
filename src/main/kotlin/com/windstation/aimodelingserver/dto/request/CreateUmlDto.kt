package com.windstation.aimodelingserver.dto.request

data class CreateUmlDto(
    var projectId: Long,
    var title: String,
    var umlCode: String,
)
