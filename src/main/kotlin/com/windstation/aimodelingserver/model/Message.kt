package com.windstation.aimodelingserver.model

import com.fasterxml.jackson.annotation.JsonIgnore
import com.windstation.aimodelingserver.enums.MessageRoleEnum
import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "messages")
data class Message(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = -1,

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    val role: MessageRoleEnum? = null,

    @Lob
    @Column(columnDefinition = "TEXT", nullable = false)
    val content: String? = null,

    @ManyToOne
    @JoinColumn(name = "chat_id", nullable = false)
    @JsonIgnore
    val chat: Chat? = null,

    val createdAt: LocalDateTime = LocalDateTime.now(),

)