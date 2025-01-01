package com.alphaStore.user

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/user")
class HealthCheck {

    @GetMapping("/healthCheck")
    fun healthCheck() : String {
        return "I Am Up"
    }

}