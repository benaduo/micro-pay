package com.nano.payment.callback;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.jdbc.autoconfigure.DataSourceAutoConfiguration;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@EnableAsync
public class PaymentCallbackApiApplication {
    public static void main(String[] args) {
        SpringApplication.run(PaymentCallbackApiApplication.class, args);
    }
}
