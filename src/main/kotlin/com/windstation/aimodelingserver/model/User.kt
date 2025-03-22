package com.windstation.aimodelingserver.model

import com.fasterxml.jackson.annotation.JsonIgnore
import jakarta.persistence.*
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

@Entity
@Table(name = "users")
data class User(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = -1,

    @Column(nullable = false, unique = true)
    var account: String? = null,

    @Column(nullable = false)
    @JsonIgnore
    @JvmField
    var password: String? = null,

    @OneToMany(mappedBy = "user", cascade = [CascadeType.ALL], orphanRemoval = true)
    @JsonIgnore
    val chats: MutableList<Chat> = mutableListOf(),

    @ManyToMany(fetch = FetchType.EAGER, cascade = [CascadeType.ALL])
    val roles: MutableSet<Role> = mutableSetOf()

) : UserDetails {

    override fun getUsername() = account!!

    override fun getAuthorities(): MutableCollection<out GrantedAuthority> =
        roles.map { SimpleGrantedAuthority(it.name) }.toMutableList()

    override fun getPassword() = password

}