package org.G346;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
@MapperScan("org.G346.mapper")
public class LogisticsCenterApplication {

    public static void main(String[] args) {
        final ConfigurableApplicationContext run = SpringApplication.run(LogisticsCenterApplication.class, args);
    }

}
