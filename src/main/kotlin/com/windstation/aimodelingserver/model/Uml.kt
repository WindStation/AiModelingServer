package com.windstation.aimodelingserver.model

import com.fasterxml.jackson.annotation.JsonIgnore
import jakarta.persistence.*
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.LocalDateTime

@Entity
@Table(name = "umls")
@EntityListeners(AuditingEntityListener::class)
data class Uml(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = -1,

    @Column(nullable = false)
    var title: String? = null,

    @Lob
    @Column(columnDefinition = "TEXT", nullable = false)
    var umlCode: String? = null,

    @ManyToOne
    @JoinColumn(name = "project_id", nullable = false)
    @JsonIgnore
    val project: Project? = null,

    @CreatedDate
    val createdAt: LocalDateTime = LocalDateTime.now(),

    @LastModifiedDate
    var updatedAt: LocalDateTime = LocalDateTime.now()

)
