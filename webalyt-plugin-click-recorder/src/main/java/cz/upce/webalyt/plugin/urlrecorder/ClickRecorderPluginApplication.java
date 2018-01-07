package cz.upce.webalyt.plugin.urlrecorder;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableDiscoveryClient
@EnableJpaRepositories
@ComponentScan("cz.upce.webalyt")
public class ClickRecorderPluginApplication {

    public static void main(String[] args) {
        SpringApplication.run(ClickRecorderPluginApplication.class, args);
    }
}
