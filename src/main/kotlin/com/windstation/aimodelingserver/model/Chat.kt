package com.windstation.aimodelingserver.model

import com.fasterxml.jackson.annotation.JsonIgnore
import jakarta.persistence.*
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.LocalDateTime

@Entity
@Table(name = "chats")
@EntityListeners(AuditingEntityListener::class)
data class Chat (

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = -1,

    var title: String = "新聊天",

    @OneToMany(mappedBy = "chat", cascade = [CascadeType.ALL], orphanRemoval = true)
    @JsonIgnore
    val messages: MutableList<Message> = mutableListOf(),

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    @JsonIgnore
    val user: User? = null,

    @CreatedDate
    val createdAt: LocalDateTime = LocalDateTime.now(),

    @LastModifiedDate
    var updatedAt: LocalDateTime = LocalDateTime.now()

)