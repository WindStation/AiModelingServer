package com.windstation.aimodelingserver.controller

import com.windstation.aimodelingserver.dto.request.CreateProjectDto
import com.windstation.aimodelingserver.dto.request.CreateUmlDto
import com.windstation.aimodelingserver.dto.request.UpdateUmlDto
import com.windstation.aimodelingserver.model.Project
import com.windstation.aimodelingserver.model.Uml
import com.windstation.aimodelingserver.services.UmlService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.security.SecurityRequirement
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api")
@SecurityRequirement(name = "BearerAuth")
@Tag(name = "uml", description = "生成UML图片的相关接口")
class UmlController(
    private val umlService: UmlService,
) {

    @PostMapping("/uml/generate", consumes = [MediaType.TEXT_PLAIN_VALUE])
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

    @GetMapping("/project/{id}/umls")
    fun getUmlsForProject(@PathVariable("id") projectId: Long): ResponseEntity<List<Uml>> {
        return ResponseEntity.ok(umlService.findAllUmlsInProject(projectId))
    }

    @GetMapping("/uml/{id}")
    fun getUmlById(@PathVariable("id") umlId: Long): ResponseEntity<Uml> {
        return ResponseEntity.ok(umlService.findUmlById(umlId))
    }

    @PostMapping("/project/uml")
    fun createUml(@RequestBody createUmlDto: CreateUmlDto): ResponseEntity<Uml> {
        return ResponseEntity.ok(umlService.createUmlInProject(createUmlDto))
    }

    @DeleteMapping("/project/uml/{id}")
    fun deleteUml(@PathVariable id: Long): ResponseEntity<Void> {
        umlService.deleteUmlInProject(id)
        return ResponseEntity.ok().build()
    }

    @PutMapping("/uml/update")
    fun updateUml(@RequestBody dto: UpdateUmlDto): ResponseEntity<Uml> {
        return ResponseEntity.ok(umlService.updateUmlInProject(dto))
    }

}