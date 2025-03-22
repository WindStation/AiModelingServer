package com.windstation.aimodelingserver.repository

import com.windstation.aimodelingserver.model.Chat
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface ChatRepository : JpaRepository<Chat, Long> {
    @Query("SELECT c FROM Chat c LEFT JOIN FETCH c.messages WHERE c.user.id = :userId ORDER BY c.updatedAt DESC")
    fun findByUserIdOrderByUpdatedAtDesc(userId: Long): List<Chat>
}