package com.windstation.aimodelingserver.enums

import io.swagger.v3.oas.annotations.media.Schema

@Schema(enumAsRef = true)
enum class MessageRoleEnum {
    User,
    Assistant,
}