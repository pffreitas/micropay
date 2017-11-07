package br.com.micropay.gateway

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.web.support.SpringBootServletInitializer
import org.springframework.context.annotation.ComponentScan

@SpringBootApplication
@ComponentScan(["br.com.micropay"])
class Application extends SpringBootServletInitializer {


    static void main(String[] args) {
        SpringApplication.run(Application.class, args)
    }


}
