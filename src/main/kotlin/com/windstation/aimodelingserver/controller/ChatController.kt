package com.windstation.aimodelingserver.controller

import com.windstation.aimodelingserver.dto.request.AppendMessageDto
import com.windstation.aimodelingserver.dto.request.CreateChatDto
import com.windstation.aimodelingserver.dto.request.DeleteChatDto
import com.windstation.aimodelingserver.dto.request.UpdateChatDto
import com.windstation.aimodelingserver.model.Chat
import com.windstation.aimodelingserver.model.User
import com.windstation.aimodelingserver.services.ChatService
import io.swagger.v3.oas.annotations.security.SecurityRequirement
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api")
@SecurityRequirement(name = "BearerAuth")
@Tag(name = "Chat", description = "聊天相关接口")
class ChatController(
    val chatService: ChatService,
) {

    @GetMapping("/user/chats")
    fun getChatsForSelf(@AuthenticationPrincipal user: User): ResponseEntity<List<Chat>> {
        return ResponseEntity.ok(chatService.findUserChats(user.id))
    }

    @GetMapping("/user/chat/{id}")
    fun getChatForSelfById(@AuthenticationPrincipal user: User, @PathVariable id: Long): ResponseEntity<Chat> {
        val chat = chatService.findById(id)
        if (chat.user!!.id == user.id) {
            return ResponseEntity.ok(chat)
        }
        throw NotFoundException()
    }

    @GetMapping("/user/{id}/chats")
    @PreAuthorize("hasRole('ADMIN')")
    fun getChatsForUser(@PathVariable id: Long): ResponseEntity<List<Chat>> {
        return ResponseEntity.ok(chatService.findUserChats(id))
    }

    @GetMapping("/chat/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    fun getChatById(@PathVariable id: Long): ResponseEntity<Chat> {
        return ResponseEntity.ok(chatService.findById(id))
    }

    @PostMapping("/chat/create")
    fun createChat(@RequestBody dto: CreateChatDto, @AuthenticationPrincipal user: User): ResponseEntity<Chat> {
        if (dto.userId == null) {
            dto.userId = user.id
        }
        return ResponseEntity.ok(chatService.createChatAndInit(dto))
    }

    @PutMapping("/chat/append")
    fun appendChatMessage(@RequestBody dto: AppendMessageDto): ResponseEntity<Chat> {
        return ResponseEntity.ok(chatService.appendMessage(dto))
    }

    @PostMapping("/chat/update")
    fun updateChat(@RequestBody dto: UpdateChatDto): ResponseEntity<Chat> {
        return ResponseEntity.ok(chatService.updateChat(dto))
    }

    @DeleteMapping("/chat/delete")
    fun deleteChat(@RequestBody dto: DeleteChatDto) {
        chatService.deleteChat(dto)
    }

}