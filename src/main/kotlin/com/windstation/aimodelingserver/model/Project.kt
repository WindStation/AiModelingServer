package com.windstation.aimodelingserver.model

import com.fasterxml.jackson.annotation.JsonIgnore
import jakarta.persistence.*

@Entity
@Table(name = "projects")
data class Project (

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = -1,

    @Column(nullable = false)
    var name: String? = null,

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    @JsonIgnore
    val user: User? = null,

    @OneToMany(mappedBy = "project", cascade = [CascadeType.ALL], orphanRemoval = true)
    @JsonIgnore
    var umls: MutableList<Uml> = mutableListOf(),

)