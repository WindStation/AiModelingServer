package com.windstation.aimodelingserver.services

import com.windstation.aimodelingserver.dto.request.AppendMessageDto
import com.windstation.aimodelingserver.dto.request.CreateChatDto
import com.windstation.aimodelingserver.dto.request.DeleteChatDto
import com.windstation.aimodelingserver.dto.request.UpdateChatDto
import com.windstation.aimodelingserver.enums.MessageRoleEnum
import com.windstation.aimodelingserver.model.Chat
import com.windstation.aimodelingserver.model.Message
import com.windstation.aimodelingserver.repository.ChatRepository
import com.windstation.aimodelingserver.repository.MessageRepository
import com.windstation.aimodelingserver.repository.UserRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class ChatService(
    private val chatRepository: ChatRepository,
    private val messageRepository: MessageRepository,
    private val userRepository: UserRepository,
) {

    /**
     * 向一个聊天中追加一条消息
     * @param dto: `AppendMessageDto`
     */
    fun appendMessage(dto: AppendMessageDto): Chat {
        val chat = findById(dto.chatId)
        val message = Message(role = dto.role, chat = chat, content = dto.content)
        chat.messages.add(message)
        return chatRepository.save(chat)
    }

    /**
     * 为用户创建一个新聊天，添加这次的首轮对话消息消息，并返回
     * @param dto: 记录聊天所属的用户ID，和用户首条提问消息
     * @return 新创建的聊天实体
     */
    fun createChatAndInit(dto: CreateChatDto): Chat {
        val user = userRepository.findById(dto.userId!!).orElseThrow()
        val chat = chatRepository.save(Chat(user = user).apply {
            if (dto.title != null) {
                this.title = dto.title!!
            }
        })
        chat.messages.add(Message(
            chat = chat,
            role = MessageRoleEnum.User,
            content = dto.message
        ))

        return chatRepository.save(chat)
    }

    /**
     * 按先后访问顺序返回用户的所有历史聊天
     * @param userId: 用户ID
     * @return 用户的聊天列表
     */
    fun findUserChats(userId: Long): List<Chat> {
        return chatRepository.findByUserIdOrderByUpdatedAtDesc(userId)
    }


    fun findById(chatId: Long): Chat {
        return chatRepository.findById(chatId).orElseThrow()
    }

    fun updateChat(dto: UpdateChatDto): Chat {
        val chat = findById(dto.chatId)
        chat.title = dto.title
        return chatRepository.save(chat)
    }

    fun deleteChat(dto: DeleteChatDto) {
        chatRepository.deleteById(dto.chatId)
    }

}