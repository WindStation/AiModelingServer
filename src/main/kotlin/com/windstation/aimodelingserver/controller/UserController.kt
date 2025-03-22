package com.windstation.aimodelingserver.controller

import com.windstation.aimodelingserver.model.User
import io.swagger.v3.oas.annotations.security.SecurityRequirement
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.ResponseEntity
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/user")
@SecurityRequirement(name = "BearerAuth")
@Tag(name = "user", description = "用户信息相关接口")
class UserController {

    @GetMapping("/self")
    fun self(@AuthenticationPrincipal user: User): ResponseEntity<User> {
        return ResponseEntity.ok(user)
    }

}