package com.muyu.newhire;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableJpaAuditing
@EnableTransactionManagement
@SpringBootApplication
public class NewhireApplication {

    public static void main(String[] args) {
        SpringApplication.run(NewhireApplication.class, args);
    }

}
