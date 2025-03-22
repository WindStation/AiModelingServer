package com.windstation.aimodelingserver

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaAuditing

@SpringBootApplication
@EnableJpaAuditing
class AiModelingServerApplication

fun main(args: Array<String>) {
    runApplication<AiModelingServerApplication>(*args)
}
