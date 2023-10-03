package br.com.rcp.ordermanager.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = "br.com.rcp.ordermanager")
@EnableJpaRepositories("br.com.rcp.ordermanager.ordersimpl.repository")
@EntityScan("br.com.rcp.ordermanager.orders.models")
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
