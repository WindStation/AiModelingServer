package com.windstation.aimodelingserver.model

import com.fasterxml.jackson.annotation.JsonIgnore
import jakarta.persistence.*

@Entity
@Table(name = "umls")
data class Uml(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = -1,

    @Column(nullable = false)
    var umlCode: String? = null,

    @ManyToOne
    @JoinColumn(name = "project_id")
    @JsonIgnore
    val project: Project? = null,

)
