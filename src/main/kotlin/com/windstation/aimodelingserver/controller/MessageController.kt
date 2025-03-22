package com.windstation.aimodelingserver.controller

import com.windstation.aimodelingserver.model.Message
import com.windstation.aimodelingserver.services.MessageService
import io.swagger.v3.oas.annotations.security.SecurityRequirement
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api")
@SecurityRequirement(name = "BearerAuth")
@Tag(name = "message", description = "聊天消息相关接口")
class MessageController(
    val messageService: MessageService,
) {

    @GetMapping("/chat/{id}/messages")
    fun getChatMessages(@PathVariable id: Long): ResponseEntity<List<Message>> {
        return ResponseEntity.ok(messageService.findChatMessages(id))
    }

}