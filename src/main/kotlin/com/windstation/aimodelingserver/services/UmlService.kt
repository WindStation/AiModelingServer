package com.windstation.aimodelingserver.services

import com.windstation.aimodelingserver.dto.request.CreateUmlDto
import com.windstation.aimodelingserver.dto.request.UpdateUmlDto
import com.windstation.aimodelingserver.model.Uml
import com.windstation.aimodelingserver.repository.ProjectRepository
import com.windstation.aimodelingserver.repository.UmlRepository
import net.sourceforge.plantuml.FileFormat
import net.sourceforge.plantuml.FileFormatOption
import net.sourceforge.plantuml.SourceStringReader
import org.springframework.stereotype.Service
import java.io.ByteArrayOutputStream

@Service
class UmlService(
    private val umlRepository: UmlRepository,
    private val projectRepository: ProjectRepository,
) {

    fun generateUml(plantUmlCode: String): ByteArray {
        val reader = SourceStringReader(plantUmlCode)
        val outputStream = ByteArrayOutputStream()

        reader.outputImage(outputStream, FileFormatOption(FileFormat.PNG))

        return outputStream.toByteArray()
    }

    fun createUmlInProject(dto: CreateUmlDto): Uml {
        val targetProject = projectRepository.findById(dto.projectId).orElseThrow()
        return umlRepository.save(Uml(
            title = dto.title,
            umlCode = dto.umlCode,
            project = targetProject
        ))
    }

    fun findAllUmlsInProject(projectId: Long): List<Uml> {
        return umlRepository.findAllByProjectId(projectId)
    }

    fun findUmlById(umlId: Long): Uml {
        return umlRepository.findById(umlId).orElseThrow()
    }

    fun deleteUmlInProject(umlId: Long) {
        umlRepository.deleteById(umlId)
    }

    fun updateUmlInProject(dto: UpdateUmlDto): Uml {
        val uml = umlRepository.findById(dto.umlId).orElseThrow()

        if (!dto.title.isNullOrBlank()) {
            uml.title = dto.title
        }

        if (!dto.umlCode.isNullOrBlank()) {
            uml.umlCode = dto.umlCode
        }

        return umlRepository.save(uml)
    }

}