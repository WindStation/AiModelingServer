package com.windstation.aimodelingserver.config

import com.windstation.aimodelingserver.enums.MessageRoleEnum
import com.windstation.aimodelingserver.model.Chat
import com.windstation.aimodelingserver.model.Message
import com.windstation.aimodelingserver.enums.RoleEnum
import com.windstation.aimodelingserver.model.Role
import com.windstation.aimodelingserver.model.User
import com.windstation.aimodelingserver.repository.ChatRepository
import com.windstation.aimodelingserver.repository.RoleRepository
import com.windstation.aimodelingserver.repository.UserRepository
import org.springframework.context.ApplicationListener
import org.springframework.context.event.ContextRefreshedEvent
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Component
class MockLoader(
    private val roleRepository: RoleRepository,
    private val userRepository: UserRepository,
    private val passwordEncoder: PasswordEncoder,
    private val chatRepository: ChatRepository
) : ApplicationListener<ContextRefreshedEvent> {

    var isSetup: Boolean = false

    @Transactional
    override fun onApplicationEvent(event: ContextRefreshedEvent) {
        if (isSetup) {
            return
        }

        for (role in RoleEnum.entries) {
            createRoleIfNotExists("ROLE_${role.name}")
        }

        createMockUserIfNotExists()

        isSetup = true
    }

    @Transactional
    fun createRoleIfNotExists(name: String): Role {
        val role = roleRepository.findByName(name).orElseGet {
            roleRepository.save(Role(name = name))
        }
        return role
    }

    @Transactional
    fun createMockUserIfNotExists(): User {
        return userRepository.findByAccount("admin").orElseGet {
            val adminRole = roleRepository.findByName("ROLE_ADMIN").get()

            val user = userRepository.save(User(
                account = "admin",
                password = passwordEncoder.encode("admin"),
                roles = mutableSetOf(adminRole)
            ))

            val chat = chatRepository.save(Chat(user = user))

            chat.messages.add(Message(role = MessageRoleEnum.User, content = "你好！- User", chat = chat))
            chat.messages.add(Message(role = MessageRoleEnum.Assistant, content = "你好！- Assistant", chat = chat))

            user.chats.add(chat)

            userRepository.save(user)
        }
    }

}