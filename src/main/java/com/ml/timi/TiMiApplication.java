package com.ml.timi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement
@SpringBootApplication
@EnableAsync
public class TiMiApplication {

    public static void main(String[] args) {
        SpringApplication.run(TiMiApplication.class, args);
    }

}
