package com.windstation.aimodelingserver.services

import com.windstation.aimodelingserver.model.Message
import com.windstation.aimodelingserver.repository.MessageRepository
import org.springframework.stereotype.Service

@Service
class MessageService(
    val messageRepository: MessageRepository,
) {

    /**
     * 返回一个聊天中的所有消息
     * @param chatId: 聊天的ID
     * @return 该聊天中的所有消息
     */
    fun findChatMessages(chatId: Long): List<Message> {
        return messageRepository.findAllByChatId(chatId)
    }

}