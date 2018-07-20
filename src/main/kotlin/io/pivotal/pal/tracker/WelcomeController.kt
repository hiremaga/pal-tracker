package io.pivotal.pal.tracker

import org.springframework.beans.factory.annotation.Value
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class WelcomeController(@Value("\${WELCOME_MESSAGE}") private val welcomeMessage: String) {
    @GetMapping("/")
    fun sayWelcomeMessage() = welcomeMessage
}
