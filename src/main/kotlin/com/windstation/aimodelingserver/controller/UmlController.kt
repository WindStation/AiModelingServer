package com.windstation.aimodelingserver.controller

import com.windstation.aimodelingserver.services.UmlService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.security.SecurityRequirement
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/uml")
@SecurityRequirement(name = "BearerAuth")
@Tag(name = "uml", description = "生成UML图片的相关接口")
class UmlController(
    private val umlService: UmlService,
) {

    @PostMapping("/generate", consumes = [MediaType.TEXT_PLAIN_VALUE])
    @Operation(
        responses = [ApiResponse(
            responseCode = "200",
            content = [Content(
                mediaType = "image/png",
                schema = Schema(implementation = ByteArray::class, format = "binary"),
            )]
        )]
    )
    fun generateUml(@RequestBody umlCode: String): ResponseEntity<ByteArray> {
        return ResponseEntity.ok()
            .contentType(MediaType.IMAGE_PNG)
            .body(umlService.generateUml(umlCode))
    }

}