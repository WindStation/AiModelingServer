package com.windstation.aimodelingserver.dto.request

import com.windstation.aimodelingserver.enums.MessageRoleEnum

data class AppendMessageDto(
    val chatId: Long,
    val role: MessageRoleEnum,
    val content: String,
)
