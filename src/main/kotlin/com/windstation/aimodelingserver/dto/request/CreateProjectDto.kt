package com.windstation.aimodelingserver.dto.request

data class CreateProjectDto(
//    var userId: Long?,    // 用户ID使用Controller的鉴权用户对象
    var title: String,
    var description: String?,
)
