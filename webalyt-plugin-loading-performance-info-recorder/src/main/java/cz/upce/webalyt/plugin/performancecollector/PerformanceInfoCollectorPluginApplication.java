package cz.upce.webalyt.plugin.performancecollector;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableDiscoveryClient
@ComponentScan("cz.upce.webalyt")
@EntityScan("cz.upce.webalyt")
@EnableJpaRepositories(basePackages = {"cz.upce.webalyt"})
public class PerformanceInfoCollectorPluginApplication {

    public static void main(String[] args) {
        SpringApplication.run(PerformanceInfoCollectorPluginApplication.class, args);
    }
}
