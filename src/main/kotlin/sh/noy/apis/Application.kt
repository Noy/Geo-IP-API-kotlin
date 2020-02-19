// @author Noy Hillel
package sh.noy.apis

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.KotlinModule
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary

@SpringBootApplication
open class Application {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            SpringApplication.run(Application::class.java, *args)
        }
    }

    @Configuration
    open class ObjectMapperConfiguration {
        val mapper = ObjectMapper().registerModule(KotlinModule())

        @Bean
        @Primary
        open fun objectMapper() = ObjectMapper().apply {
            registerModule(KotlinModule())
        }
    }
}
