package kr.kakaocloud.kakeulgae;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@ConfigurationPropertiesScan
@SpringBootApplication
public class KakeulgaeApplication {

    public static void main(String[] args) {
        SpringApplication.run(KakeulgaeApplication.class, args);
    }

}
