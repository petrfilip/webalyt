package cz.upce.webalyt.plugin.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableDiscoveryClient
@ComponentScan("cz.upce.webalyt")
public class WebAnalyticsApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebAnalyticsApplication.class, args);
    }
}
