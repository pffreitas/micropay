package br.com.micropay.gateway

import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory
import org.springframework.amqp.rabbit.connection.ConnectionFactory
import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.web.support.SpringBootServletInitializer
import org.springframework.context.ConfigurableApplicationContext
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.core.env.Environment

import javax.annotation.Resource

@SpringBootApplication
@ComponentScan(["br.com.micropay"])
class Application extends SpringBootServletInitializer {

    @Resource
    private Environment env


    static void main(String[] args) {
        final ConfigurableApplicationContext ctx = SpringApplication.run(Application.class, args)
    }

    /**
     * Cria o connectionFactory para o rabbitMq.
     *
     * @return ConnectionFactory
     */
    @Bean
    ConnectionFactory connectionFactory() {
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory(env.getRequiredProperty("rabbit.host"))
        connectionFactory.setUsername(env.getRequiredProperty("rabbit.user"))
        connectionFactory.setPassword(env.getRequiredProperty("rabbit.password"))
        connectionFactory.setPort(Integer.parseInt(env.getRequiredProperty("rabbit.port")))
        return connectionFactory
    }

    /**
     * Cria o SimpleRabbitListenerContainerFactory para o rabbitMq.
     *
     * @return SimpleRabbitListenerContainerFactory
     */
    @Bean(name = "rabbitListenerContainerFactory")
    SimpleRabbitListenerContainerFactory listenerFactory() {
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory()
        factory.setConnectionFactory(connectionFactory())
        factory.setConcurrentConsumers(env.getRequiredProperty("rabbit.numberOfListeners", Integer.class))
        return factory
    }

    /**
     * Cria o RabbitTemplate do rabbitMq.
     *
     * @return RabbitTemplate
     */
    @Bean
    RabbitTemplate rabbitTemplate() {
        return new RabbitTemplate(connectionFactory())
    }


}
