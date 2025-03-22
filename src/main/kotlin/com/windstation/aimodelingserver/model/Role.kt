package com.windstation.aimodelingserver.model

import jakarta.persistence.*

@Entity
@Table(name = "roles")
data class Role (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = -1,

    @Column(nullable = false, unique = true)
    val name: String? = null,
)