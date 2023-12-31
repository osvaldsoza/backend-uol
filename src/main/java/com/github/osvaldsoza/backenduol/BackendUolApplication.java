package com.github.osvaldsoza.backenduol;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class BackendUolApplication {

    public static void main(String[] args) {
        SpringApplication.run(BackendUolApplication.class, args);
    }

}
