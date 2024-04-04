package org.example;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.example.auditspringbootstarter.EnableAudit;

@SpringBootApplication
@EnableTransactionManagement
@EnableAudit
public class MeterServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(MeterServiceApplication.class, args);

    }
}
