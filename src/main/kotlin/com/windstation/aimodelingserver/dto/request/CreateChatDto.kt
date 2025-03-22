package com.windstation.aimodelingserver.dto.request

data class CreateChatDto (
    var userId: Long?,
    var title: String? = "新聊天",
    val message: String
)