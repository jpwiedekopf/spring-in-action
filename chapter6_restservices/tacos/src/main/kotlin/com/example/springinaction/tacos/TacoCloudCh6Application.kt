package com.example.springinaction.tacos

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorViewResolver
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.http.HttpStatus
import org.springframework.web.servlet.ModelAndView

@SpringBootApplication
class TacoCloudCh6Application {
    @Bean
    fun supportPathBasedLocationStrategyWithoutHashes(): ErrorViewResolver =
        ErrorViewResolver { _, _, _ ->
            ModelAndView(
                "index.html",
                mapOf<String, Any>(),
                HttpStatus.OK
            )
        }
}

fun main(args: Array<String>) {
    runApplication<TacoCloudCh6Application>(*args)
}