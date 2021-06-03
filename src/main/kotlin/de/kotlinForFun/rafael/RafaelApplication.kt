package de.kotlinForFun.rafael

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class RafaelApplication {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            runApplication<RafaelApplication>(*args)
        }
    }
}
