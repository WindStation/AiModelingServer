package com.windstation.aimodelingserver.services

import net.sourceforge.plantuml.FileFormat
import net.sourceforge.plantuml.FileFormatOption
import net.sourceforge.plantuml.SourceStringReader
import org.springframework.stereotype.Service
import java.io.ByteArrayOutputStream

@Service
class UmlService {

    fun generateUml(plantUmlCode: String): ByteArray {
        val reader = SourceStringReader(plantUmlCode)
        val outputStream = ByteArrayOutputStream()

        reader.outputImage(outputStream, FileFormatOption(FileFormat.PNG))

        return outputStream.toByteArray()
    }

}