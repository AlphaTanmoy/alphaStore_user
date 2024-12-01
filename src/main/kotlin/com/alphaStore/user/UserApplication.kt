package com.alphaStore.user

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.openfeign.EnableFeignClients
import org.springframework.scheduling.annotation.EnableScheduling

@EnableFeignClients(
	basePackages = [
		"com.alphaStore.user"
	])
@SpringBootApplication
@EnableScheduling
class UserApplication

fun main(args: Array<String>) {
	runApplication<UserApplication>(*args)
}
