package com.windstation.aimodelingserver.repository

import com.windstation.aimodelingserver.model.Message
import org.springframework.data.jpa.repository.JpaRepository

interface MessageRepository : JpaRepository<Message, Long> {
    fun findAllByChatId(chatId: Long): List<Message>
}